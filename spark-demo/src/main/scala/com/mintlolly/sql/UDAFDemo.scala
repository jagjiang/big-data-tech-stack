package com.mintlolly.sql

import org.apache.spark.sql.{Encoder, Encoders, Row, SparkSession, functions}
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, IntegerType, StringType, StructField, StructType}

/**
 * Create by on jiangbo 2021/1/12 9:56
 *
 * Description:
 *
 */
object UDAFDemo {


  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("UDAFDEMO").master("local[*]").getOrCreate()

    val sc = spark.sparkContext

    import spark.implicits._


    val rdd = sc.makeRDD(List(("张三", 11), ("李四", 21), ("王五", 32)))
    val df = rdd.toDF("name", "age")

    //继承UserDefinedAggregateFunction
    spark.udf.register("myavg", new UDAFAVGDemo)
    df.createOrReplaceTempView("info")
    spark.sql("select myavg(age) from info").show()
    //继承Aggregator[User,AgeBuffer,Double]
    val ds = df.as[People]
    //创建聚合函数
    val udafStrong = new UDAFStrongTypeDemo
    //将聚合函数转换为查询的列
    val col = udafStrong.toColumn
    ds.select(col).show()

    //spark 3.0支持的
    //spark.udf.register("avgAge", functions.udaf(udafStrong))
  }
}

case class AgeBuffer(total: Int, count: Int)

case class People(name: String, age: Int)

class UDAFAVGDemo extends UserDefinedAggregateFunction {
  override def inputSchema: StructType = StructType(
    Array(
      StructField("age", IntegerType)
    )
  )

  override def bufferSchema: StructType = StructType(
    Array(
      StructField("total", IntegerType),
      StructField("count", IntegerType)
    )
  )

  override def dataType: DataType = DoubleType

  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer.update(0, 0)
    buffer.update(1, 0)
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) = buffer.getInt(0) + input.getInt(0)
    buffer(1) = buffer.getInt(1) + 1
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getInt(0) + buffer2.getInt(0)
    buffer1(1) = buffer1.getInt(1) + buffer2.getInt(1)
  }

  override def evaluate(buffer: Row): Double = {
    buffer.getInt(0).toDouble / buffer.getInt(1)
  }
}

/**
 * 强类型聚合函数
 */
class UDAFStrongTypeDemo extends Aggregator[People,AgeBuffer,Double]{
  override def zero: AgeBuffer = {
    AgeBuffer(0,0)
  }

  override def reduce(b: AgeBuffer, a: People): AgeBuffer = {
    val total = b.total+a.age
    val count = b.count+1
    AgeBuffer(total,count)
  }

  override def merge(b1: AgeBuffer, b2: AgeBuffer): AgeBuffer = {
    AgeBuffer(b1.total+b2.total,b1.count+b2.count)
  }

  override def finish(reduction: AgeBuffer): Double = {
    reduction.total.toDouble / reduction.count
  }

  //DataSet 默认额编解码器，用于序列化，固定写法
  //自定义类型就是 product 自带类型根据类型选择
  override def bufferEncoder: Encoder[AgeBuffer] = {
    Encoders.product
  }

  override def outputEncoder: Encoder[Double] = {
    Encoders.scalaDouble
  }
}
