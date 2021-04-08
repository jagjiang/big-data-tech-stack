package com.mintlolly.service.util

import org.apache.spark.rdd.RDD

/**
 * Create by on jiangbo 2021/1/5 10:50
 *
 * Description:将算法中的通用方法提取出来，比如管制区占用分析，生态保护红线占用分析等等，提取公用方法
 *
 */
object AnalysisUtil {
  def wordCount(lines:RDD[String])={
    val words = lines.flatMap(_.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
    words
  }
}
