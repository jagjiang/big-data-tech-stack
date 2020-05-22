package com.mintlolly.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Create by on jiangbo 2020/5/22 9:59
 * <p>
 * Description:
 */
public class GroupConsumer {
    
    private KafkaConsumer<String, String> consumer;
    private final int id;
    public GroupConsumer(int id){
        this.id = id;
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"slave2:6667,master:6667");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG,"client-"+id);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"another-consumer");
        //earliest 从提交的offset开始消费；无提交的offset时，从头开始消费 ，如果是一个新的消费者，或消费者组那么就会从头开始消费
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        //关闭自动提交offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        //自动提交offset，上一个参数设置为true，每1000ms一次的频率定期持久化offset
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton("input-topic"));
    }

    public void consume(){
        while (true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(100));
            for (ConsumerRecord<String, String> record : consumerRecords) {
                System.out.printf("id = %d , partition = %d , offset = %d, key = %s, value = %s%n",
                        id, record.partition(), record.offset(), record.key(), record.value());
            }
            //commitSync同步提交，所谓的同步，指的是Consumer会一直等待提交offset成功，
            //在此期间不能继续拉取以及消费消息
            //如果提交失败，consumer会一直重复尝试提交，直到超时，默认的时间是60秒

            //异步提交异步提交不会阻塞消费者线程，提交失败的时候不会进行重试，
            // 但是我们可以为异步提交创建一个监听器，在提交失败的时候进行重试，下面的代码是注册监听器的代码
            //
            consumer.commitAsync(new RetryOffsetCommitCallback(consumer));

            //除了固定频率提交offset外，kafka在关闭consumer的时候也会提交offset
            consumer.close();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 8; i++) {
            final int id = i;
            new Thread() {
                @Override
                public void run() {
                    new GroupConsumer(id).consume();
                }
            }.start();
        }
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }
}
