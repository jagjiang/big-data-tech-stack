package com.mintlolly;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KafkaOperation {

    private static final String INPUT_TOPIC = "input-topic";
    private static final String OUTPUT_TOPIC = "output-topic";

    //创建topic
    public static void createTopic(){
        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        AdminClient adminClient = AdminClient.create(properties);
        List<String> list = new ArrayList<String>();
        list.add("test");
        deleteTopic(adminClient,list);
        final short relicationFactor =1;
        final List<NewTopic> newTopics = Arrays.asList(
                new NewTopic(INPUT_TOPIC, 2, relicationFactor));
        for (NewTopic newTopic : newTopics) {
            System.out.println(newTopic);
        }
        adminClient.createTopics(newTopics);
    }
    //删除topic
    public static void deleteTopic(final AdminClient adminClient, final List<String> topicsToDelete){
        adminClient.deleteTopics(topicsToDelete);
    }
    //生产者
    public static void provider(){

    }
    //消费者
    public static void consummer(){

    }
    public static void main(String[] args) {
        createTopic();
    }
}
