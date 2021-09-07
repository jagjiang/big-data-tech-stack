package com.mintlolly.learn.transform;

import com.mintlolly.bean.SensorReading;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * Created on 2021/8/20
 *
 * @author jiangbo
 * Description:
 */
public class Transform {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStream<SensorReading> dataStream = env.fromCollection(Arrays.asList(
                new SensorReading("sensor_01", 1629273689L, 33.5),
                new SensorReading("sensor_02", 1629273935L, 34.2),
                new SensorReading("sensor_03", 1629274035L, 35.3),
                new SensorReading("sensor_04", 1629274931L, 36.4),
                new SensorReading("sensor_04", 1629274932L, 37.9),
                new SensorReading("sensor_04", 1629274933L, 37.8)));
        //map算子
        DataStream<SensorReading> map = dataStream.map(new MapFunction<SensorReading, SensorReading>() {
            @Override
            public SensorReading map(SensorReading value) throws Exception {
                value.setId("map_" + value.getId());
                return value;
            }
        });
//        map.print("map");
        //flatmap
        DataStream<String> flatmap = map.flatMap(new FlatMapFunction<SensorReading, String>() {
            @Override
            public void flatMap(SensorReading value, Collector<String> out) throws Exception {
                String[] fields = value.getId().split("_");
                for (String word:fields) {
                    out.collect(word);
                }
            }
        });
//        flatmap.print("flatmap");
        //filter
        SingleOutputStreamOperator<SensorReading> filter = map.filter(new FilterFunction<SensorReading>() {
            @Override
            public boolean filter(SensorReading value) throws Exception {
                return value.getTemperature() > 33.3;
            }
        });
//        filter.print("filter");
        //keyby
        KeyedStream<SensorReading, String> keyby = map.keyBy(new KeySelector<SensorReading, String>() {
            @Override
            public String getKey(SensorReading value) throws Exception {
                return value.getId();
            }
        });
//        keyby.print("keyby");
        DataStream<SensorReading> reduce = keyby.reduce(new ReduceFunction<SensorReading>() {
            @Override
            public SensorReading reduce(SensorReading value1, SensorReading value2) throws Exception {
                value1.setTimestamp(value2.getTimestamp());
                if (value2.getTemperature() > value1.getTemperature()) {
                    value1.setTemperature(value2.getTemperature());
                }
                return value1;
            }
        });
//        reduce.print("reduce");
        //split （1.12已经不存在了 processFunction）DataStream ->SplitStream:根据某些特征把一个DataStream拆分成两个或者多个DataStream
        //select splitStream->DataStream:从一个SplitStream中获取一个或者多个DataStream
        //Connect DataStream,DataStream -> ConnectedStreams:连接两个保持他们类型的数据流，两个数据流被Connect之后，
        //被放到同一个工作流中，内部依然保持各自的数据和形式不发生任何变化，两个流相互独立
        ConnectedStreams<SensorReading, SensorReading> connectedStreams = keyby.connect(reduce);
        DataStream<Object> coMap = connectedStreams.map(new CoMapFunction<SensorReading, SensorReading, Object>() {
            @Override
            public Object map1(SensorReading value) throws Exception {
                if(value.getTemperature() > 37){
                    return value;
                }else {
                    return "map1大于37°";
                }
            }

            @Override
            public Object map2(SensorReading value) throws Exception {
                if(value.getTemperature() < 37){
                    return value;
                }else {
                    return "map2小于37°";
                }
            }
        });
//        coMap.print("coMap");
        //union DataStream ->DataStream:对两个或者两个以上的DataStream进行Union操作
        //Connect 与 Union 区别:
        //Union之前两个流的类型必须是一样的，Connect可以不一样，在之后的coMap中再去调整成为一样的
        //Connect只能操作两个流，Union可以操作多个。
        env.execute();

    }
}
