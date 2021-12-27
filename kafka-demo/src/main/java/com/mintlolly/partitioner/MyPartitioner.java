package com.mintlolly.partitioner;

import com.mintlolly.bean.Student;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.Random;

public class MyPartitioner implements Partitioner {
    private int passline;
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //这里只是做验证，生产中不能直接指定分区，那分区就没什么意义了
        //尝试返回了一个没有的分区 5，消息发送失败了。
        if(Integer.parseInt(key.toString()) > passline) return 0;
        int[] partitions = {1,2};
        Random random = new Random();
        int index = random.nextInt(2);
        return partitions[index];
    }

    @Override
    public void close() {
        System.out.println("关闭分区器");
    }

    @Override
    public void configure(Map<String, ?> map) {
        passline = Integer.parseInt(map.get("pass.line").toString());
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
