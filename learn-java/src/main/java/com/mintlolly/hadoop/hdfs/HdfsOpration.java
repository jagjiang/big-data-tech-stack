package com.mintlolly.hadoop.hdfs;

import com.fasterxml.jackson.databind.ser.std.FileSerializer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created on 2021/11/9
 *
 * @author jiangbo
 * Description:
 */
public class HdfsOpration {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        System.setProperty("HADOOP_USER_NAME","hadoop");
        FileSystem fileSystem = FileSystem.get(conf);
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("hdfs://master:8020/"));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus.getPath());
        }
        System.setProperty("user","hadoop");
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("hdfs://master:8020/data/local/windows"));
        fsDataOutputStream.write("hello".getBytes(StandardCharsets.UTF_8));
//        fileSystem.copyFromLocalFile(new Path("E:\\test-data\\output1214"),new Path("E:\\test-data\\output1214"));
//
//        boolean exists = fileSystem.exists(new Path("E:\\test-data\\student"));
//        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("E:\\test-data\\output1214"));
//        fsDataOutputStream.write("hello".getBytes(StandardCharsets.UTF_8));
//        FSDataInputStream open = fileSystem.open(new Path("E:\\test-data\\student\\*"));
//        IOUtils.copyBytes(open,fsDataOutputStream,1024,true);
//        System.out.println(exists);
    }

}
