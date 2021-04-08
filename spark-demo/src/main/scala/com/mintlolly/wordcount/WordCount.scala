package com.mintlolly.wordcount

import org.apache.spark.sql.SparkSession

/**
 * Create by on jiangbo 2020/10/15 10:11
 *
 * Description:
 *
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("WordCount").master("local[*]").getOrCreate()

    val sc = spark.sparkContext

    val list = List(12,33,44,55,66,22,66,222)

    /**
     * 源码:
     * length=6 numSlices = 3
     * 0 until 3
     * (start,end)
     * (0,2) i = 0
     * (2,4) i = 1
     * (4,6)  i = 2
     * 结果：
     * part1 12 33
     * part2 44 55
     * part3 66 22
     * @param length
     * @param numSlices
     * @return
     */
    def positions(length: Long, numSlices: Int): Iterator[(Int, Int)] = {
      (0 until numSlices).iterator.map { i =>
        val start = ((i * length) / numSlices).toInt
        val end = (((i + 1) * length) / numSlices).toInt
        (start, end)
      }
    }
//    sc.makeRDD(list,3).saveAsTextFile("logs/output")


    /**
     * def defaultMinPartitions: Int = math.min(defaultParallelism, 2)
     * local[*] 8个,2小所以是两个分区
     * 分区数  最小分区数量
     * 1
     * 2
     * 3 这样就会有3个分区，最小两个，会超过minpartition
     *
     * 文件分区计算方式
     * totalSize  文件大小 test.log 7个字节
     * long goalSize = totalSize / (numSplits == 0 ? 1 : numSplits);
     * goalSize = 7/2= 3 byte  每个分区3个字节
     *
     * 7个字节/3个字节 = 2个分区余1个字节 hadoop 1.1倍，
     * 剩余大于分区10%才会分区  1/3 > 0.1 需要新的分区，所以就有3个分区了
     *
     * 数据分区
     * part3却没有数据
     *
     * spark读取文件是用的hadoop方式 一行行读，和字节数无关
     * 数据读取以偏移量为单位,偏移量不会重复读取
     * 1@@ =>偏移量对应1->0,@->1,@->2  换行符占用两个字节
     * 2@@ =>345  换行符占用两个字节
     * 3 =>6
     * 0 =>[0,3]  0到3字节左闭右闭 又是按行读 所以就把1,2读到了
     * 1 =>[3,6]  第6个字节
     * 2 =>[6,7]
     *
     */

//    sc.textFile("logs/test.log",2)
//      .saveAsTextFile("logs/textfileout")
//
    sc.makeRDD(list).map((_,null)).reduceByKey((x,y) => x).map(_._1).foreach(println)
  }
}
