package com.mintlolly.learn.base;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2021/8/18
 *
 * @author jiangbo
 * Description:
 */
public class TextSource {
    final static Logger log = LoggerFactory.getLogger(TextSource.class);

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> stringDataStreamSource = env.readTextFile("./logs/log_info.log");
        stringDataStreamSource.print();

        env.execute();
    }
}
