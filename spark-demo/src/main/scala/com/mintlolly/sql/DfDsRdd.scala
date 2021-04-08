package com.mintlolly.sql

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

/**
 * Create by on jiangbo 2021/1/15 15:00
 *
 * Description:
 *
 */



object DfDsRdd {
  case class People(name:String,age:Int)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DfDsRdd").master("local[*]").getOrCreate()
    import spark.implicits._
    val sc = spark.sparkContext
    val rdd = sc.makeRDD(List(("kitty", 22), ("dog", 24), ("Mickey", 88)))

    //构建DF方式二
    val rowRDD = rdd.map(f => {
      Row(f._1, f._2)
    })

    val df = spark.createDataFrame(rowRDD, StructType(Array(
      StructField("name", StringType),
      StructField("age", IntegerType)
    )))
    df.show()

    //rdd -> dataframe -> dataset
    val dataframe = rdd.toDF("name", "age")
    dataframe.show()

    val ds = dataframe.as[People]
    ds.show()

    //dataset ->dataframe -> rdd
    val ds2df = ds.toDF()
    ds2df.show()

    val frame2RDD = dataframe.rdd
    frame2RDD.foreach(f =>{
      println( f.get(0) + ":" + f.get(1))

    })

    //dataset -> rdd
    val ds2RDD = ds.rdd
    ds2RDD.foreach(f =>{
      println(f.name +":" + f.age)
    })


  }
}
