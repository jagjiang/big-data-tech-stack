package com.mintlolly

import org.apache.spark.sql.SparkSession

/**
 * Create by on jiangbo 2020/7/16 13:13
 *
 * Description:
 * mapPartitions 与map类似，不过是以分区的形式进行处理 Iterator
 * mapPartitionsWithIndex 与mapPartitions类似，加了分区位置的索引值
 * aggregateByKey 分区内根据key值聚合，然后再进行分区外聚合
 *
 * reduceByKey,aggregateByKey,foldByKey,combineByKey底层均使用了
 *
 *    combineByKeyWithClassTag[U](
 *       (v: V) => cleanedSeqOp(createZero(), v),
 *       cleanedSeqOp,
 *       combOp,
 *       partitioner)
 *
 */
object Transformation {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Transformation").master("local[4]").getOrCreate()
    val sc = spark.sparkContext

    sc.setLogLevel("ERROR")

    val pairRdd = sc.parallelize(List(("cat", 2), ("cat", 5), ("mouse", 4),
      ("cat", 12), ("dog", 8), ("mouse", 2)), 2)

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
      if(index == 1){
        partitionsMap.iterator //只要第一个分区的返回值
      }else{
        Nil.iterator  //else返回空集合
      }
    }).foreach(println)

    /**
     * mapPartitons
     */
    println("=======================mapPartitions=========================")
    var index = 1
    pairRdd.mapPartitions(item =>{
      var result = List[(String,Int)]()

      while(item.hasNext){
        result = item.next() :: result
      }
      result =("|",index) :: result
      index = index+ 1
      result.iterator
    }).foreach(println)
    println("=======================aggregateByKey说明======================")
    //aggregateBykey 按照key进行聚合
    pairRdd.aggregateByKey(0)((a, b) => {
      println("分区内：" + a + "," + b)
      math.max(a, b)
    }, (x, y) => {
      println("分区外combOP：" + x + "," + y)
      x + y
    }).foreach(println)
    println("===================aggregateByKey======================================")
    //_+_,分区内相加，_*_整体相乘
    pairRdd.aggregateByKey(0)(_+_, _*_).foreach(println)
    //aggregateByKey最终返回数据结果和初始值的类型保持一致
    //获取相同key平均值
    pairRdd.aggregateByKey((0,0))((t,v)=>{
      (t._1+v,t._2+1)
    },(t1,t2)=>{
      (t1._1+t2._1,t1._2+t2._2)
    }).foreach(println)
    println("=======================foldByKey=======================================")
    //当分区内计算规则和分区间计算规则相同时，aggregateByKey 就可以简化为 foldByKey
    pairRdd.foldByKey(0)(_+_).foreach(println)

    println("==========================combineByKey====================================")
    //combineByKey方法需要三个参数
    //第一个参数表示：将相同key的第一个数据进行结构的转换，实现操作
    //第二个参数表示:分区内的计算规则
    //第三个参数表示:分区间的计算规则
    //分区内求平均值
    val value = pairRdd.combineByKey(
      v =>(v,1),
      (t:(Int,Int),v) =>{
        (t._1+v,t._2+1)
      },
      (t1:(Int,Int),t2:(Int,Int))=>{
        (t1._1+t2._1,t1._2+t2._2)
      }
    ).foreach(println)

    println("============================flatMap====================================")
    val flatRDD = sc.makeRDD(List(List(1, 2), 3, List(4, 5)))
    //flatmap  TraversableOnce 集合的模板特征，该特征只能被遍历一次或一次或多次
    flatRDD.flatMap(f=>{
      f match {
        case list:List[_] => list
        case data => List(data)
      }
    }).foreach(println)
    val ints = List("123", "123", "123", "123")
    ints.foreach(println)
    //为什么ints.flatMap(_)不可以 https://blog.csdn.net/iteye_6988/article/details/82644211
    ints.flatMap(f=>f).foreach(println)

    println("============================glom====================================")
    //将同一个分区的数据直接转换为相同类型的内存数组进行处理，分区不变
    //计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
    pairRdd.glom().map(f=>{
        f.max
    }).foreach(println)

    println("============================zip====================================")
    //拉链，两个数据源分区数量和数据数量一定要相同不然报错，scala的拉链不分这个
    val rdd1 = sc.makeRDD(List(12, 34, 56, 78))
    val rdd2 = sc.makeRDD(List(34, 24, 36, 58))
    rdd1.zip(rdd2).foreach(println)

    println("============================partationBy============================")
    val partitionByRDD = sc.makeRDD(Array((1, "aaa"), (2, "bbb"), (3, "ccc")), 3)
//    partitionByRDD.partitionBy(new HashPartitioner(2)).saveAsTextFile("./logs/partition")

    println("============================join============================")
    //有可能造成笛卡尔积，慎用
    val rdd3 = sc.makeRDD(List(('a',1),('b',2),('v',3),('b',22),('b',45)))
    val rdd4 = sc.makeRDD(List(('a',3),('b',1),('v',6),('b',88),('b',120)))
    rdd3.join(rdd4).foreach(println)

    println("============================cogroup============================")
    rdd3.cogroup(rdd4).foreach(println)
    println("============================union============================")
    val unionRDD = rdd1.union(rdd2)
    println("============================distinct============================")
    //distinct底层用的reducebykey key为源数据，value为null,最后返回key ._1
    unionRDD.distinct().foreach(println)
  }
}
