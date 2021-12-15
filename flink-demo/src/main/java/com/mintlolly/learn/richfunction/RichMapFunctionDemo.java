package com.mintlolly.learn.richfunction;

import com.mintlolly.utils.MyKafkaUtil;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * Created on 2021/11/30
 *
 * @author jiangbo
 * Description:
 */
public class RichMapFunctionDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment().setParallelism(5);
        DataStreamSource<String> intDS = env.addSource(MyKafkaUtil.getKafkaSource("topic_log", "test"));
        intDS.map(Integer::valueOf)
                .map(new MyRichFunction())
                .setParallelism(6)
                .print();
        env.execute();
    }

    public static class MyRichFunction extends RichMapFunction<Integer, Integer> {

        @Override
        public void open(Configuration parameters) throws Exception {
            System.out.println("默认生命周期方法, 初始化方法, 在每个并行度上只会被调用一次, 而且先被调用");
        }

        @Override
        public void close() throws Exception {
            System.out.println("默认生命周期方法,  最后一个方法, 做一些清理工作, 在每个并行度上只调用一次, 而且是最后被调用");
        }

        @Override
        public Integer map(Integer value) throws Exception {
            System.out.println("map...一个元素执行一次");
            return value * value;
        }
    }
}
