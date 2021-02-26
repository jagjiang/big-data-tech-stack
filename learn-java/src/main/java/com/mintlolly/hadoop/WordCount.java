package com.mintlolly.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Create by on jiangbo 2021/2/3 16:17
 * <p>
 * Description:
 */
public class WordCount {
    public static final String HDFS_URL = "";
    public static final String HADOOP_USER_NAME = "hdfs";

    int max = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        System.setProperty("HADOOP_USER_NAME", HADOOP_USER_NAME);
        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", HDFS_URL);

        // 创建一个 Job
        Job job = Job.getInstance(conf);
        // 设置运行的主类
        job.setJarByClass(WordCount.class);
        // 设置 Mapper 和 Reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReduce.class);

        //想要使用 combiner 功能只要在组装作业时，添加下面一行代码即可：
        job.setCombinerClass(WordCountReduce.class);

        // 设置 Mapper 输出 key 和 value 的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 设置 Reducer 输出 key 和 value 的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 如果输出目录已经存在，则必须先删除，否则重复运行程序时会
        FileSystem fileSystem = FileSystem.get(new URI(HDFS_URL),conf,HADOOP_USER_NAME);
        Path outputPath = new Path("");
        if(fileSystem.exists(outputPath)){
            fileSystem.delete(outputPath,true);
        }
        // 设置作业输入文件和输出文件的路径
        FileInputFormat.setInputPaths(job,new Path("input"));

        FileOutputFormat.setOutputPath(job,outputPath);

        //将作业提交到集群并等待它完成，参数设置为true代表打印显示对应的进度
        boolean result = job.waitForCompletion(true);

        fileSystem.close();
// 根据作业结果,终止当前运行的 Java 虚拟机,退出程序
        System.exit(result ? 0 : -1);
    }
}
