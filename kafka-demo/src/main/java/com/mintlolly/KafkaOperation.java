package com.mintlolly;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaOperation {

    static AdminClient adminClient;

    static {
        Properties properties = new Properties();
        //通过server.properties 查看端口，hdp3.1并没有采用默认的9092   listeners=PLAINTEXT://slave2:6667
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "master:6667,slave2:6667,slave3:6667");
        adminClient = AdminClient.create(properties);
    }

    /**
     * 创建topics
     */
    public static void createTopic(List<String> topics){

        final int numPartitions = 2;
        final short relicationFactor =2;

        ArrayList<NewTopic> list = new ArrayList<>();
        topics.forEach(topic -> list.add(
                new NewTopic(topic, numPartitions, relicationFactor)
        ));
        CreateTopicsResult createTopicsResult = adminClient.createTopics(list);
        System.out.println(createTopicsResult);
    }

    /**
     * 删除topic
     * @param topicsToDelete 需要删除lopics List<String>
     */
    public static void deleteTopic(List<String> topicsToDelete){
        adminClient.deleteTopics(topicsToDelete);
    }

    /**
     * 列出所有topics
     */
    public static void topicLists() {
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        try {
            System.out.println(listTopicsResult.names().get());
            System.out.println(listTopicsResult.listings().get());

            ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
            //是否列出内部使用的topic
            listTopicsOptions.listInternal(true);
            System.out.println(listTopicsResult.names().get());
            System.out.println(listTopicsResult.listings().get());
        }catch (InterruptedException |ExecutionException e){
            e.printStackTrace();
        }
    }
    //生产者
    public static void provider(){

    }
    //消费者
    public static void consummer(){

    }
    public static void main(String[] args) {
        String INPUT_TOPIC = "input-topic";
        String OUTPUT_TOPIC = "output-topic";
        ArrayList<String> topics = new ArrayList<>();
        topics.add(INPUT_TOPIC);
        topics.add(OUTPUT_TOPIC);

        //创建topics
//        createTopic(topics);
        //列出所有topics
//        topicLists();
        //删除topics
//        deleteTopic(topics);
//        topicLists();
        adminClient.close();
    }
}
