package com.mintlolly.streaming

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SPARK_VERSION, SparkConf, SparkContext}

/**
 * Created on 2022/1/17
 *
 * @author jiangbo
 *         Description:
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[3]").setAppName("WordCount")
    val sc = new SparkContext(conf)
//    sc.setLogLevel("warn")
    val ssc = new StreamingContext(sc,Seconds(2))

    val value = ssc.socketTextStream("101.42.251.112", 9999)
    val wordCount = value.map(f => {
      (f, 1)
    }).reduceByKey(_+_).cache()
    wordCount.print()
      wordCount.map(f =>{
        f._1.toCharArray
      }).flatMap(f =>f).print()

    ssc.start()
    ssc.awaitTermination()



  }
}
