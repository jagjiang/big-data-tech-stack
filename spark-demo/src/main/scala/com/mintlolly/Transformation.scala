package com.mintlolly

import org.apache.spark.sql.SparkSession

/**
 * Create by on jiangbo 2020/7/16 13:13
 *
 * Description:
 *
 */
object  Transformation {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Transformation").master("local[4]").getOrCreate()
    val sc = spark.sparkContext
    val pairRdd = sc.parallelize(List(("cat",2),("cat",5),("mouse",4),
      ("cat",12),("dog",12),("mouse",2)),2)
    //pairRdd这个RDD有两个分区，一个区存放的是
    //("cat",2),("cat",5),("mouse",4)
    //另一个区存放的是
    //("cat",12),("dog",12),("mouse",2)

    //aggregateBykey 按照key进行聚合
    pairRdd.aggregateByKey(0)(math.max(_,_),_+_).foreach(println)

    pairRdd.aggregateByKey(0)((a,b) =>{
      println("分区内："+a+","+b)
      math.max(a,b)
    },(x,y)=>{
      println("分区外combOP："+x+","+y)
      x+y
    }).foreach(println)
    //_+_,分区内相加，_*_整体相乘
    pairRdd.aggregateByKey(0)(_+_,_*_).foreach(println)
  }
}
