package com.mintlolly.sql

import org.apache.spark.sql.SparkSession

/**
 * Created on 2021/11/9
 *
 * @author jiangbo
 *         Description:
 */
object SparkSQL {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[8]").appName("SparkSQL").getOrCreate()

    val csvTest = spark.read.format("csv").option("header", "true").csv("E:\\test-data\\student.csv")
    val value = csvTest.repartition(4)
    value.rdd.mapPartitions(f =>{
      f.filter(_.toString().contains("22"))
    })
  csvTest.printSchema()
    val jsonTest = spark.read.json("E:\\test-data\\test.json")
    jsonTest.show()
    jsonTest.printSchema()
//    value.write.option("header", "true").csv("E:\\test-data\\student")

  }
}
