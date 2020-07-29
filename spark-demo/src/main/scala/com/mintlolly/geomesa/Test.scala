package com.mintlolly.geomesa

import java.util

import com.typesafe.config.ConfigFactory
import org.apache.hadoop.conf.Configuration
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}
import org.geotools.data.Query
import org.locationtech.geomesa.spark.GeoMesaSpark
import org.locationtech.geomesa.spark.jts._
import org.locationtech.jts.geom._

import scala.collection.JavaConversions._

/**
 * Create by on jiangbo 2020/7/27 9:11
 *
 * Description:
 *
 */
object Test {


  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.WARN)

    val spark = SparkSession.builder().appName("GeomesaTest").master("local[3]")
      .config("LOGLEVEL","warn").getOrCreate()
    import spark.implicits._

    val sc = spark.sparkContext

//    spark.withJTS
//
//    val schema = StructType(Array(
//      StructField("id", StringType, nullable = false),
//      StructField("Lon", DoubleType, nullable = false),
//      StructField("Lat", DoubleType, nullable = false)
//    ))
//
//    val dataFile = "C:\\Users\\Ryanj\\Desktop\\point.csv"
//
//    val df = spark.read.schema(schema).option("header","true").csv(dataFile)
//    df.show()
//
//    val alteredDF = df.withColumn("point", st_makePoint($"Lon", $"Lat"))
//    alteredDF.show


    val exampleConf = ConfigFactory.load("G:\\github_topic\\study\\spark-demo\\src\\main\\resources\\example.conf").root().render()

    val params = Map(
      "geomesa.converter"        -> exampleConf,
      "geomesa.converter.inputs" -> "C:\\Users\\Ryanj\\Desktop\\example.csv",
      "geomesa.sft"              -> "phrase:String,dtg:Date,geom:Point:srid=4326",
      "geomesa.sft.name"         -> "example")

    val query = new Query("example")
    val rdd = GeoMesaSpark(params).rdd(new Configuration(), sc, params, query)
    rdd.foreach(println)
  }
}
