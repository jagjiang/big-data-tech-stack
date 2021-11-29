package com.mintlolly.utils;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * Created on 2021/11/29
 *
 * @author jiangbo
 * Description:
 */
public class MyKafkaUtil {
    private static final Properties config = new Properties();
    static {
        String brokerList = "master:9092,slave1:9092,slave2:9092";
        config.setProperty("bootstrap.servers", brokerList);
    }
    public static FlinkKafkaProducer<String> getKafkaSink(String topic){
       return new FlinkKafkaProducer<String>(topic,new SimpleStringSchema(),config);
    }

    public static FlinkKafkaConsumer<String> getKafkaSource(String topic,String groupId){
        config.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        return new FlinkKafkaConsumer<String>(topic,new SimpleStringSchema(),config);
    }

}
