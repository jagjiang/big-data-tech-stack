package com.mintlolly.client;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class KafkaOperation {

    final static Logger logger  = LoggerFactory.getLogger(KafkaOperation.class);

    static AdminClient adminClient;

    static {
        Properties properties = new Properties();
        //通过server.properties 查看端口，hdp3.1并没有采用默认的9092   listeners=PLAINTEXT://slave2:6667
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        adminClient = AdminClient.create(properties);
    }

    /**
     * 创建topics
     */
    public static void createTopic(List<String> topics){

        final int numPartitions = 1;
        final short relicationFactor =1;

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

    /**
     * 获取topic的配置信息   配置信息可以通过代码修改，在log文件的info5月19日的时候有配置信息的详细内容。
     * @param topicName
     */
    public static void describeConfig(String topicName){
        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC,topicName);
        DescribeConfigsResult describeConfigsResult = adminClient.describeConfigs(Arrays.asList(configResource));

        try{
            describeConfigsResult.all().get().forEach((k,v)->
                    logger.info("name:"+k.name()+",desc:"+v+"\n"));
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    /**
     * 增加Partition数量，目前Kafka不支持删除或减少Partition
     */
    public static void incrPartitions(String topicName,int partitionNum){
        Map<String, NewPartitions> newPartitions = new HashMap<>();
        // 将MyTopic的Partition数量调整为2
        newPartitions.put(topicName, NewPartitions.increaseTo(partitionNum));
        CreatePartitionsResult result = adminClient.createPartitions(newPartitions);
        try{
            System.out.println(result.all().get());
        }catch (ExecutionException | InterruptedException e){
            logger.error(e.getMessage(),e);
        }

    }

    public static void main(String[] args) {
        String INPUT_TOPIC = "input-topic";
        String OUTPUT_TOPIC = "output-topic";
        ArrayList<String> topics = new ArrayList<>();
        topics.add(INPUT_TOPIC);
        topics.add(OUTPUT_TOPIC);

        //创建topics
        createTopic(topics);
        //列出所有topics
//        topicLists();
        //删除topics
//        deleteTopic(topics);
//        topicLists();
        //获取topic的配置信息
//        describeConfig(INPUT_TOPIC);
        //调整topic的partition 目前只允许增加
//        incrPartitions(INPUT_TOPIC,2);

        adminClient.close();
    }
}
