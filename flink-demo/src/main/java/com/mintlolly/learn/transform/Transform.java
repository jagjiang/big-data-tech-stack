package com.mintlolly.learn.transform;

import com.mintlolly.bean.SensorReading;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
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
                new SensorReading("sensor_02", 1629273935L, 33.2),
                new SensorReading("sensor_03", 1629274035L, 33.3),
                new SensorReading("sensor_04", 1629274931L, 33.4),
                new SensorReading("sensor_04", 1629274932L, 33.9),
                new SensorReading("sensor_04", 1629274933L, 33.8)));
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
        reduce.print("reduce");
        env.execute();
    }
}
