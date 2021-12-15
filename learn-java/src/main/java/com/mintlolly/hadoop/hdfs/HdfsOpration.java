package com.mintlolly.hadoop.hdfs;

import com.fasterxml.jackson.databind.ser.std.FileSerializer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created on 2021/11/9
 *
 * @author jiangbo
 * Description:
 */
public class HdfsOpration {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(conf);

        boolean exists = fileSystem.exists(new Path("E:\\test-data\\student"));
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("E:\\test-data\\output1214"));
        FSDataInputStream open = fileSystem.open(new Path("E:\\test-data\\student\\*"));
        IOUtils.copyBytes(open,fsDataOutputStream,1024,true);
        System.out.println(exists);
    }

}
