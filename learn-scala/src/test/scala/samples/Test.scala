package samples

import com.mintlolly.hbase._
import org.apache.spark.sql.SparkSession

/**
 *
 * @description
 * @date 2019/12/13 10:42 
 * @author jiangbo
 *
 */
object Test extends HBaseReadSupport {
  def main(args: Array[String]): Unit = {



    // filterKeys  contains 使用
    val map1 = Map(("a", "2"), ("z", "2"), ("c", "2"), ("d", "2"), ("e", "2"), ("f", "2"))

    val map2 = Map(("q", "A"), ("b", "b"), ("z", "b"))

    map1 filterKeys map2.contains foreach {
      println
    }

    val myMap = Map("baidu" ->"www.baidu.com","google" ->"www.google.com")

    def show(x: Option[String]) = x match {
      case Some(s) => s
      case None => "?"
    }

    println(show(myMap.get("baidu")))
    println(show(myMap.get("sogou")))


    Map("keyefs" zip "value":_*).foreach(println)


    val values: Map[String, Set[String]] = Map("family1"->Set("set1test1","set1test2","set1test3"),"family2"->Set("set2test1","set2test2"))

    for{
      (family,contents) <-values
      content <- contents
    }{
      println(family)
      println(content)
    }

//    val spark = SparkSession.builder().appName("HbaseTest").master("local[*]").getOrCreate()
//    val sc = spark.sparkContext
//    implicit val config = HBaseConfig(("hbase.zookeeper.quorum", "slave1,slave2,slave3")
//      , ("hbase.zookeeper.property.clientPort", "2181")
//      , ("zookeeper.znode.parent", "/hbase-unsecure"))
//    val hbaseSC = new HbaseSC(sc)
//
//    val admin = Admin()
//    admin.disableTable("test")
//    admin.deleteTable("test")
//
//    hbaseSC.hbase[String]("zhysd").foreach(println)
  }

}
