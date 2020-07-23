package com.mintlolly

import java.util

import org.apache.spark.sql.SparkSession

/**
 * Create by on jiangbo 2020/7/16 13:13
 *
 * Description:
 * mapPartitions 与map类似，不过是以分区的形式进行处理 Iterator
 * mapPartitionsWithIndex 与mapPartitions类似，加了分区位置的索引值
 * aggregateByKey 分区内根据key值聚合，然后再进行分区外聚合
 *
 */
object Transformation {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Transformation").master("local[4]").getOrCreate()
    val sc = spark.sparkContext
    val pairRdd = sc.parallelize(List(("cat", 2), ("cat", 5), ("mouse", 4),
      ("cat", 12), ("dog", 12), ("mouse", 2)), 2)

    /**
     * mapPartitionsWithIndex
     * 通过下面的方法可以查看每个partition存储的内容是什么
     * pairRdd这个RDD有两个分区，一个区存放的是
     * ("cat",2),("cat",5),("mouse",4)
     * 另一个区存放的是
     * ("cat",12),("dog",12),("mouse",2)
    */

    pairRdd.mapPartitionsWithIndex((index, partitionIterator) => {
      val partitionsMap = scala.collection.mutable.Map[Int, List[(String, Int)]]()
      var partitionList = List[(String, Int)]()
      while (partitionIterator.hasNext) {
        partitionList = partitionIterator.next() :: partitionList
      }
      partitionsMap(index) = partitionList
      partitionsMap.iterator //返回值
    }).foreach(println)

    /**
     * mapPartitons
     */
    println("===================mapPartitions Test===================")
    var index = 1
    pairRdd.mapPartitions(item =>{
      var result = List[(String,Int)]()

      while(item.hasNext){
        result = item.next() :: result
      }
      result =("|",index) :: result
      index += 1
      result.iterator
    }).foreach(println)

    //aggregateBykey 按照key进行聚合
    pairRdd.aggregateByKey(0)(math.max(_, _), _ + _).foreach(println)

    pairRdd.aggregateByKey(0)((a, b) => {
      println("分区内：" + a + "," + b)
      math.max(a, b)
    }, (x, y) => {
      println("分区外combOP：" + x + "," + y)
      x + y
    }).foreach(println)

    //_+_,分区内相加，_*_整体相乘
    pairRdd.aggregateByKey(0)(_ + _, _ * _).foreach(println)
  }
}
