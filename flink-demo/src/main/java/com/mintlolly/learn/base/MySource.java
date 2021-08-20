package com.mintlolly.learn.base;

import com.mintlolly.bean.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Random;

/**
 * Created on 2021/8/20
 *
 * @author jiangbo
 * Description:
 */
public class MySource implements SourceFunction<SensorReading> {
    @Override
    public void run(SourceContext<SensorReading> ctx) throws Exception {
        SensorReading sensorReading = new SensorReading("sensor_01",System.currentTimeMillis(), new Random(40).nextDouble());
        ctx.collect(sensorReading);
    }

    @Override
    public void cancel() {

    }

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<SensorReading> dataStream = env.addSource(new MySource());

        dataStream.print();

        env.execute();
    }
}
