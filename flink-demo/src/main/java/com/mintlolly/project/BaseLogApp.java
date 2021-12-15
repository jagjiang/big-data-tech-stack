package com.mintlolly.project;

import com.alibaba.fastjson.JSONObject;
import com.mintlolly.utils.MyKafkaUtil;
import netscape.javascript.JSObject;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.text.SimpleDateFormat;

/**
 * Created on 2021/11/29
 *
 * @author jiangbo
 * Description:
 */
public class BaseLogApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);
        env.setStateBackend(new FsStateBackend("hdfs://master:8020/flink/baseLog"));
        env.enableCheckpointing(10000L, CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setAlignmentTimeout(60000L);
        System.setProperty("HADOOP_USER_NAME","root");

        //2.读取 Kafka ods_base_log 主题数据
        String topic = "ods_base_log";
        String groupId = "ods_dwd_base_log_app";
        DataStreamSource<String> kafkaDS = env.addSource(MyKafkaUtil.getKafkaSource(topic, groupId));
        SingleOutputStreamOperator<JSONObject> jsonObj = kafkaDS.map(JSONObject::parseObject);

        KeyedStream<JSONObject, String> keyedStream = jsonObj.keyBy(data -> data.getJSONObject("common").getString("mid"));
        keyedStream.map(new RichMapFunction<JSONObject, JSONObject>() {
            //状态编程，声明状态用于当前Mid是否已经访问过
            private ValueState<String> firstVisitDateState;
            private SimpleDateFormat simpleDateFormat;
            @Override
            public void open(Configuration parameters) throws Exception {
                firstVisitDateState = getRuntimeContext().getState(new ValueStateDescriptor<String>("new-mid", String.class));
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }

            @Override
            public JSONObject map(JSONObject value) throws Exception {
                //取出新用户标记
                String isNew = value.getJSONObject("common").getString("is_new");
                //如果当前前端传输数据表示为新用户,则进行校验
                if ("1".equals(isNew)) {
                //取出状态数据并取出当前访问时间
                    String firstDate = firstVisitDateState.value();
                    Long ts = value.getLong("ts");
                //判断状态数据是否为 Null
                    if (firstDate != null) {
                //修复
                        value.getJSONObject("common").put("is_new", "0");
                    } else {
                //更新状态
                        firstVisitDateState.update(simpleDateFormat.format(ts));
                    }
                }
                //返回数据
                return value;
            }
        });
        env.execute();

    }
}
