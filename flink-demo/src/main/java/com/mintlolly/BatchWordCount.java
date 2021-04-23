package com.mintlolly;


import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.SortPartitionOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create by on jiangbo 2021/1/21 14:03
 * <p>
 * Description:
 * @author jiangbo
 */
public class BatchWordCount {

    public static final Logger LOG =  LoggerFactory.getLogger(BatchWordCount.class);

    public static void main(String[] args) throws Exception {
        //创建执行环境
        ExecutionEnvironment flinkEnv = ExecutionEnvironment.getExecutionEnvironment();
        LOG.info("读取数据");
        DataSource<String> stringDataSource = flinkEnv.readTextFile(".\\flink-demo\\src\\main\\java\\com\\mintlolly\\BatchWordCount.java");
        LOG.info("开始计算");
        SortPartitionOperator<Tuple2<String, Integer>> wordCount = stringDataSource.flatMap(new WordCountFunc())
                .groupBy(0)
                .sum(1).sortPartition(1, Order.DESCENDING);
        LOG.info("打印结果");
        wordCount.print();
    }
}

class WordCountFunc implements FlatMapFunction<String, Tuple2<String,Integer>>{

    @Override
    public void flatMap(String lines, Collector<Tuple2<String, Integer>> out){
        String[] words = lines.split("[\\\\<>}\\[\\]{%.= :,\"();#/-]");
        for (String word : words) {
            out.collect(new Tuple2<>(word,1));
        }
    }
}