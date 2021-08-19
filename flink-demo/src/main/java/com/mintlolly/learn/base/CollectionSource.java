package com.mintlolly.learn.base;

import com.mintlolly.bean.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created on 2021/8/18
 *
 * @author jiangbo
 * Description:从集合中读取数据
 */
public class CollectionSource {
    final static Logger log = LoggerFactory.getLogger(CollectionSource.class);
    public static void main(String[] args) throws Exception {
        log.info("create environment");
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStream<SensorReading> dataStream = env.fromCollection(Arrays.asList(
                new SensorReading("sensor_01",1629273689L,33.5),
                new SensorReading("sensor_02",1629273935L,33.2),
                new SensorReading("sensor_03",1629274035L,33.3),
                new SensorReading("sensor_04",1629274935L,33.4)));

        dataStream.print();
        env.execute();
    }
}
