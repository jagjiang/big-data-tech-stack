package com.mintlolly.sql

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
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

    val rowRDD = rdd.map(f => {
      Row(f._1, f._2)
    })


    val df = spark.createDataFrame(rowRDD, StructType(Array(
      StructField("name", StringType),
      StructField("age", IntegerType)
    )))
    df.show()

    spark.udf.register("myavg", new UDAFAVGDemo)

    df.createOrReplaceTempView("info")

    spark.sql("select myavg(age) from info").show()

  }
}

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
