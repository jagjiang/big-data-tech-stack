package com.mintlolly.sql

import org.apache.spark.deploy.SparkSubmit
import org.apache.spark.sql.SparkSession

/**
 * Create by on jiangbo 2020/6/11 13:42
 *
 * Description:加载mysql数据库
 *
 */
object SQLTest {
  def main(args: Array[String]): Unit = {
    SparkSubmit
    val spark = SparkSession.builder()
      .master("local[4]")
      .appName("SQLTest")
      .getOrCreate()
    val spark1 = SparkSession.builder()
      .master("local[4]")
      .appName("SQLTest1")
      .getOrCreate()

    val sc = spark.sparkContext


    val jdbcDF = spark.read.format("JDBC")
      .option("url", "jdbc:mysql://172.16.150.128:3306/?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=true")
      .option("dbtable", "employees.employees")
      .option("user", "root")
      .option("password", "123456")
      .load()

    jdbcDF.show(2)

  }

}
