package com.mintlolly.state;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created on 2021/11/30
 *
 * @author jiangbo
 * Description:
 */
public class KeyedStateDemo {
     static class CountWithKeyedState extends RichFlatMapFunction<Tuple2<Long,Long>,Tuple2<Long,Long>>{
        private ValueState<Tuple2<Long,Long>> sum ;
        @Override
        public void open(Configuration parameters) throws Exception {
            sum = getRuntimeContext().getState(new ValueStateDescriptor<Tuple2<Long, Long>>("average", //状态名称
                    TypeInformation.of(new TypeHint<Tuple2<Long, Long>>() {})));  //状态类型
            System.out.println(sum);
        }

        @Override
        public void flatMap(Tuple2<Long, Long> input, Collector<Tuple2<Long, Long>> out) throws Exception {
            //获取当前状态值
            Tuple2<Long, Long> tmpCurrentSum = sum.value();
            //状态默认值
            if(null == tmpCurrentSum){
                tmpCurrentSum = new Tuple2<>(0L,0L);
            }
            System.out.println("sum.value:" +sum.value());
            //更新
            Tuple2<Long,Long> newSum = new Tuple2<>(tmpCurrentSum.f0 + input.f0,tmpCurrentSum.f1 +input.f1);
            //更新状态值
            sum.update(newSum);
            //如果count >=3 清空状态值
            if (newSum.f0 >= 3) {
                out.collect(new Tuple2<>(input.f0, input.f1));
                sum.clear();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Tuple2<Long,Long> tuple1 = new Tuple2<Long,Long>(1L, 1L);
        Tuple2<Long,Long> tuple2 = new Tuple2<Long,Long>(1L, 2L);
        Tuple2<Long,Long> tuple3 = new Tuple2<Long,Long>(1L, 3L);
        Tuple2<Long,Long> tuple4 = new Tuple2<Long,Long>(1L, 4L);
        Tuple2<Long,Long> tuple5 = new Tuple2<Long,Long>(1L, 5L);
        Tuple2<Long,Long> tuple6 = new Tuple2<Long,Long>(1L, 6L);
        Tuple2<Long,Long> tuple7 = new Tuple2<Long,Long>(1L, 7L);
        Tuple2<Long,Long> tuple8 = new Tuple2<Long,Long>(1L, 8L);
        ArrayList<Tuple2<Long,Long>> tuples = new ArrayList<>();
        tuples.add(tuple1);
        tuples.add(tuple2);
        tuples.add(tuple3);
        tuples.add(tuple4);
        tuples.add(tuple5);
        tuples.add(tuple6);
        tuples.add(tuple7);
        tuples.add(tuple8);
        Double[] doubles = {1.0,2.0};
        DataStreamSource<Tuple2<Long, Long>> tuple2DataStreamSource = env.fromCollection(tuples);
        tuple2DataStreamSource
                .keyBy(f -> f.f0)
                .flatMap(new CountWithKeyedState())
                .setParallelism(1)
                .print();
        env.execute();
    }
}
