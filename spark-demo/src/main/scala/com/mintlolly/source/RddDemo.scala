package com.mintlolly.source

import com.huaban.analysis.jieba.JiebaSegmenter
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Create by on jiangbo 2020/7/23 9:20
 *
 * Description:
 *
 */
object RddDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("RddDemo")
    val sc = new SparkContext(conf)

    val sourceRDD = sc.textFile("C:\\Users\\Ryanj\\Desktop\\bdt-test-demo\\README.md",8)
    val rdd = sc.parallelize(Array("aaa","bbb",2,"ccc"))
    sourceRDD.map(f =>{
      //分词器的使用
      new JiebaSegmenter().sentenceProcess(f.toString.trim)
    }).foreach(println)
  }
}
