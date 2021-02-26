package com.mintlolly.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Create by on jiangbo 2021/2/3 16:26
 * <p>
 * Description:
 */
public class WordCountReduce extends Reducer<Text, IntWritable,Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable value : values) {
            count+=value.get();
        }

        context.write(key,new IntWritable(count));
    }
}
