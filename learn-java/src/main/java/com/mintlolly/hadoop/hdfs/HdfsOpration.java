package com.mintlolly.hadoop.hdfs;

import com.fasterxml.jackson.databind.ser.std.FileSerializer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

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
        System.out.println(exists);
    }

}
