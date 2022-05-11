package com.mintlolly.streaming

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created on 2022/1/18
 *
 * @author jiangbo
 *         Description:热点搜索词滑动统计，每个10秒钟，统计最近60秒钟的搜索词的搜索频次，并打印出排名最靠前的3个搜索词以及出现次数。
 */
object WindowHotWords {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WindowHotWords").setMaster("local[3]")
    //创建StreamingContext 批处理时间间隔，根据集群性能确定
    val ssc = new StreamingContext(conf,Seconds(10))

    val data = ssc.socketTextStream("101.42.251.112", 9999)
    data.map(f=>f.split(" "))
      .flatMap(f=>f)
      .map((_,1))
      .reduceByKeyAndWindow((v1:Int,v2:Int)=>v1+v2,Seconds(60),Seconds(10))
      .transform(searchWordCountsRDD =>{
        val countSearchWordsRDD = searchWordCountsRDD.map(tuple => (tuple._2, tuple._1))
        val sortedCountSearchWordsRDD = countSearchWordsRDD.sortByKey(ascending = false)
        val sortedSearchWordCountsRDD = sortedCountSearchWordsRDD.map(tuple => (tuple._1, tuple._2))
        val top3SearchWordCounts = sortedSearchWordCountsRDD.take(3)
        for (tuple <- top3SearchWordCounts) {
          println("result : " + tuple)
        }
        sortedSearchWordCountsRDD
      }).print()

    ssc.start()
    ssc.awaitTermination()

  }
}
