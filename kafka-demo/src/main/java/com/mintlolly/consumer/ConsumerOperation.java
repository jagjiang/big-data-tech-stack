package com.mintlolly.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConsumerOperation {

    final static Logger LOG = LoggerFactory.getLogger(ConsumerOperation.class);

    public static KafkaConsumer<String, String> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-test");
//        instanceId.ifPresent(id -> props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, id));
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        return consumer;
    }

    /**
     * 单线程消费
     */
    public static void consumerSingleThread() {
        KafkaConsumer<String, String> consumer = createConsumer();
        consumer.subscribe(Arrays.asList("input-topic"));
        final int minBatchSize = 10;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(100));
            for (ConsumerRecord<String, String> record : records) {
                LOG.info("offset={},partition={},value={}", record.offset(), record.partition(), record.value());
                buffer.add(record);
            }

            //比如写数据库，批量的形式进行写入
            if (buffer.size() >= minBatchSize) {
                //如写入数据库的逻辑，下面只是打印
                for (ConsumerRecord<String, String> stringStringConsumerRecord : buffer) {
                    System.out.println(stringStringConsumerRecord);
                }
                //
                consumer.commitAsync();
                //清空数据
                buffer.clear();
            }
        }
    }

    /**
     * 独立消费者模式相对小众一些
     * 独立消费者可以不设置group.id
     * 可以多线程消费，按指定分区进行消费
     */
    public static void singleConsummerPattern() {
        KafkaConsumer<String, String> consumer = createConsumer();
        KafkaConsumer<String, String> consumer1 = createConsumer();

        consumer.subscribe(Arrays.asList("input-topic"));
        consumer1.subscribe(Arrays.asList("input-topic"));
//        List<TopicPartition> list = new ArrayList<>();
//        TopicPartition topicPartition = new TopicPartition("input-topic", 0);
//        TopicPartition topicPartition1 = new TopicPartition("input-topic", 1);
//        TopicPartition topicPartition2 = new TopicPartition("input-topic", 2);
//        TopicPartition topicPartition3 = new TopicPartition("input-topic", 3);
//
//        list.add(topicPartition);
//        list.add(topicPartition1);
//        list.add(topicPartition2);
//        consumer.assign(list);
//        consumer1.assign(Arrays.asList(topicPartition3));
        while (true) {

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(100));
            for (ConsumerRecord<String, String> record : records) {
                LOG.info("consumer: offset={},partition={},key={},value={}", record.offset(), record.partition(), record.key(), record.value());
            }

            ConsumerRecords<String, String> records1 = consumer1.poll(Duration.ofSeconds(100));
            for (ConsumerRecord<String, String> record : records1) {
                LOG.info("consumer1: offset={},partition={},key={},value={}", record.offset(), record.partition(), record.key(), record.value());
            }
        }
    }

    public static void fromBeginning() {
        KafkaConsumer<String, String> consumer = createConsumer();
        TopicPartition topicPartition = new TopicPartition("input-topic", 0);
        consumer.assign(Arrays.asList(topicPartition));
        consumer.seekToBeginning(Arrays.asList(topicPartition));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(100));
            for (ConsumerRecord<String, String> record : records) {
                LOG.info("consumer: offset={},partition={},key={},value={}", record.offset(), record.partition(), record.key(), record.value());
            }
        }
    }

    public static void main(String[] args) {
//        consumerSingleThread();
//        singleConsummerPattern();
        fromBeginning();
    }
}
