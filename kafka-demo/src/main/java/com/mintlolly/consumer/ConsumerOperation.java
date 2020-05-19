package com.mintlolly.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Arrays;
import java.util.Properties;
import java.util.function.Consumer;

public class ConsumerOperation {
    public static KafkaConsumer<String, String> createConsumer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serializa-tion.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serializa-tion.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        return consumer;
    }

    public static void main(String[] args) {
        String topic = "input-topic";
        createConsumer().subscribe(Arrays.asList(topic));
        int i = 0;

        while (true) {
            ConsumerRecords<String, String> records =createConsumer().poll(100);
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("offset = %d, key = %s, value = %s\n",
                        record.offset(), record.key(), record.value());
            }
        }
    }
}
