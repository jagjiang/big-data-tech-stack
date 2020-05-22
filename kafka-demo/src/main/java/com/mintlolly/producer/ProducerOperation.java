package com.mintlolly.producer;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;

public class ProducerOperation {
    final static Logger LOG = LoggerFactory.getLogger(ProducerOperation.class);


    /**
     * 构建Producer实例
     * @return KafkaProducer
     */
    public static Producer<String,String> createProducer(){
        Properties properties = new Properties();
        //指定kafka的服务的ip地址以及端口号
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"master:6667,slave2:6667");
        //指定消息key的序列化器
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //指定消息value的序列化器
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 指定自定义的Partition负载均衡器 指定自己的分区器，比如直接返回3，那么所有的数据都会存在partition3
//        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.mintlolly.partitioner.MyPartitioner");
        return new KafkaProducer<>(properties);
    }

    /**
     * 异步发送数据
     * 发送了100条数据，对消息是否抵达完全没管，等于纯异步的方式
     */
    public static void producerAsyncSend(){
        String topicName = "input-topic";


        Producer<String, String> producer = createProducer();
        //构建消息对象
//        ProducerRecord<String,String> record = new ProducerRecord<>(topicName,key,value);
//        Future<RecordMetadata> send = producer.send(record);
        for (int i = 0; i < 100; i++) {
            LOG.info("已经发送了[{}]条数据",i);
            producer.send(new ProducerRecord<>(topicName,String.valueOf(i),"send  "+ i+ "  message"));
        }
        producer.close();
    }

    /**
     * 异步回调发送，获取发送结果
     * Callback两个参数
     * 一个RecordMetadata 消息发送成功后的元数据
     * Exception消息发送过程中的异常信息
     */
    public static void producerAsyncCallbackSend(){
        String topicName = "input-topic";

        Producer<String, String> producer = createProducer();
        for (int i = 0; i < 100; i++) {

            producer.send(new ProducerRecord<>(topicName, String.valueOf(i), "send  " + i + "  message"), ((recordMetadata, e) -> {
                if (e != null) {
                    LOG.error(e.getMessage(),e);
                }
                LOG.info(String.format(
                        "hasTimestamp: %s, timestamp: %s, hasOffset: %s, offset: %s, partition: %s, topic: %s",
                        recordMetadata.hasTimestamp(), recordMetadata.timestamp(),
                        recordMetadata.hasOffset(), recordMetadata.offset(),
                        recordMetadata.partition(), recordMetadata.topic()
                ));
            }));
        }
        producer.close();
    }


    public static void main(String[] args) {
//        producerAsyncSend();
        producerAsyncCallbackSend();
    }
}
