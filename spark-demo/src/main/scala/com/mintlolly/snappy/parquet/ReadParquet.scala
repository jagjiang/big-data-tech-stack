package com.mintlolly.snappy.parquet

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created on 2021/9/13
 *
 * @author jiangbo
 *         Description:
 */
object ReadParquet {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("ReadParquet")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    spark.read.parquet("\\test-data\\rawlog.20210223_235959.parquet").show();
//    spark.read.parquet("E:\\test-data\\dyx-offline\\20210223\\user-byte.parquet.20210223_235959").show()
  }
}
