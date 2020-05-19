package com.mintlolly.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerOperation {

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
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.mintlolly.partitioner.MyPartitioner");
        return new KafkaProducer<String, String>(properties);
    }

    /**
     * 异步发送数据
     */
    public static void producerAsyncSend(){
        String topicName = "input-topic";
        String key = "event";
        String value = "send a message!";

        Producer<String, String> producer = createProducer();
        //构建消息对象
        ProducerRecord<String,String> record = new ProducerRecord<>(topicName,key,value);
        Future<RecordMetadata> send = producer.send(record);
        //如果不flush的话会失败
        producer.flush();
        System.out.println(send.isDone());
        producer.close();
    }

    /**
     * 异步回调发送
     */
    public static void producerAsyncCallbackSend(){
        String topicName = "input-topic";
        String key = "event";
        String value = "send some message!";

        Producer<String, String> producer = createProducer();
        ProducerRecord<String,String> record = new ProducerRecord<>(topicName,key,value);
        producer.send(record,((recordMetadata, e) -> {
            if(e != null){
                e.printStackTrace();
            }
            System.out.println(String.format(
                    "hasTimestamp: %s, timestamp: %s, hasOffset: %s, offset: %s, partition: %s, topic: %s",
                    recordMetadata.hasTimestamp(), recordMetadata.timestamp(),
                    recordMetadata.hasOffset(), recordMetadata.offset(),
                    recordMetadata.partition(), recordMetadata.topic()
            ));
        }));
        producer.flush();
        producer.close();
    }


    public static void main(String[] args) {
//        producerAsyncSend();
        producerAsyncCallbackSend();
    }
}
