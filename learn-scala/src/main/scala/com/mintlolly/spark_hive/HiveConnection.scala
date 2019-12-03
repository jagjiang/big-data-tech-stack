package com.mintlolly.spark_hive

import java.text.SimpleDateFormat
import java.util.Date

import com.mintlolly.utils.TimeUtils
import org.apache.spark.sql.SparkSession

/**
 *
 * @description
 * @date 2019/12/3 10:24 
 * @author jiangbo
 *
 */
object HiveConnection {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("HiveConnection")
      .config("hive.metastore.uris","thrift://slave1:9083")
      .config("spark.sql.warehouse.dir","hdfs://192.168.200.150:9000/warehouse/tablespace/managed/hive")
      .enableHiveSupport()
      .master("local[4]")
      .getOrCreate()

    spark.udf.register("toDate10",TimeUtils.toDate10 _)
    spark.sql("show create table mobile_phone_signaling_data.bucket_signaling").show()
    spark.sql("select msid, toDate10(signaling_time) from mobile_phone_signaling_data.bucket_signaling").show()

  }
}
