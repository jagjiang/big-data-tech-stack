package com.mintlolly.sql

import org.apache.spark.sql.SparkSession

/**
 * Create by on jiangbo 2021/1/15 15:35
 *
 * Description:
 *
 */
object SparkConnHive {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkConnHive")
      .master("local[*]")
      .enableHiveSupport()
      .getOrCreate()
    val sc = spark.sparkContext

    spark.sql("select * from  class.score").show()

    spark.stop()
  }
}
