package com.mintlolly;


import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * Create by on jiangbo 2021/1/21 14:03
 * <p>
 * Description:
 *
 * @author jiangbo
 */
public class StreamWordCount {

    public static void main(String[] args) throws Exception {

        // 创建Flink的流式计算环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 监听本地9000端口
        DataStream<String> text = env.socketTextStream("192.168.200.130", 9000, "\n");

        // 将接收的数据进行拆分，分组，窗口计算并且进行聚合输出
        text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                for (String word : s.split(" ")) {
                    collector.collect(new Tuple2<>(word, 1));
                }
            }
        }).keyBy(k->k.f0).countWindow(10) .sum(1).print();

        env.execute("Flink Streaming Java API");
    }
}