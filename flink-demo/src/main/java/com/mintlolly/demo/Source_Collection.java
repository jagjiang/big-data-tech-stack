package com.mintlolly.demo;

import com.mintlolly.demo.bean.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created on 2021/7/14
 *
 * @author jiangbo
 * Description:
 */
public class Source_Collection {
    final static Logger logger = LoggerFactory.getLogger(Source_Collection.class);

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<SensorReading> data = env.fromCollection(
                Arrays.asList(new SensorReading("sensor_1", 1547718199L, 31.0),
                        new SensorReading("sensor_2", 1547718210L, 32.0),
                        new SensorReading("sensor_4", 1547718210L, 32.2),
                        new SensorReading("sensor_3", 1547718210L, 34.5)));

        data.filter(f ->f.getTemperature() > 32.0).print();

        env.execute();
    }
}
