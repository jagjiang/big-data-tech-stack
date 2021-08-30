//package com.mintlolly.geomesa
//
//import org.apache.spark.sql.SparkSession
//
///**
// * Create by on jiangbo 2020/7/27 9:11
// *
// * Description:
// *
// */
//object Test {
//
//
//  def main(args: Array[String]): Unit = {
//
////    Logger.getLogger("org").setLevel(Level.WARN)
//
//    val spark = SparkSession.builder().appName("GeomesaTest").master("local[3]")
//      .config("LOGLEVEL","warn").getOrCreate()
//    import spark.implicits._
//
//    val sc = spark.sparkContext
//
////    spark.withJTS
////
////    val schema = StructType(Array(
////      StructField("id", StringType, nullable = false),
////      StructField("Lon", DoubleType, nullable = false),
////      StructField("Lat", DoubleType, nullable = false)
////    ))
////
////    val dataFile = "C:\\Users\\Ryanj\\Desktop\\point.csv"
////
////    val df = spark.read.schema(schema).option("header","true").csv(dataFile)
////    df.show()
////
////    val alteredDF = df.withColumn("point", st_makePoint($"Lon", $"Lat"))
////    alteredDF.show
//
//    val conf = ConfigFactory.parseString(
//      """
//        | {
//        |   type         = "delimited-text",
//        |   format       = "CSV",
//        |   fields = [
//        |      { name = "phrase", transform = "concatenate($1, $2)" },
//        |      { name = "lat",    transform = "$3::double" },
//        |      { name = "lon",    transform = "$4::double" },
//        |      { name = "geom",   transform = "point($lon, $lat)" }
//        |      ]
//        | }
//        """.stripMargin)
//
//    val exampleConf = ConfigFactory.parseResources("./example.conf").root().render()
//
//    val params = Map(
//      "geomesa.converter"        -> exampleConf,
//      "geomesa.converter.inputs" -> "C:\\Users\\Ryanj\\Desktop\\example.csv",
//      "geomesa.sft"              -> "phrase:String,geom:Point:srid=4326",
//      "geomesa.sft.name"         -> "example")
//
//    val dataFrame = spark.read
//      .format("geomesa")
//      .options(params)
//      .option("geomesa.feature", "example")
//      .load()
//
//    dataFrame.withColumn("buffer",st_bufferPoint($"geom",10)).show()
//
////    val query = new Query("example")
////    val rdd = GeoMesaSpark(params).rdd(new Configuration(), sc, params, query)
//
//  }
//}
