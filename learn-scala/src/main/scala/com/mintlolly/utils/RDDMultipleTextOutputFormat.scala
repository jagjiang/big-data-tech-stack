package com.mintlolly.utils

/**
 *
 * @description 这是一个将spark rdd按照key进行分文件保存的使用
 * @date 2019/12/6 11:24 
 * @author jiangbo
 *
 */
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat
import org.apache.spark.sql.SparkSession

class RDDMultipleTextOutputFormat extends MultipleTextOutputFormat[Any, Any] {
  override def generateActualKey(key: Any, value: Any): Any =
    NullWritable.get()

  override def generateFileNameForKeyValue(key: Any, value: Any, name: String): String =
    key.toString+"/"+name
}


object RDDMultipleTextOutputFormat{
  def main(args: Array[String]): Unit = {
    val spark =SparkSession.builder()
      .appName("Write2Mysql")
      .master("local[6]")
      .getOrCreate()
    spark.sparkContext.textFile("E:\\whkj.csv").map(f =>{

      (f.split(",")(2),f)
    }).repartition(1)
      .saveAsHadoopFile("E:\\分目录保存", classOf[String], classOf[String],
      classOf[RDDMultipleTextOutputFormat])
  }
}