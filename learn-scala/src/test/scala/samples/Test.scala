package samples

import com.mintlolly.hbase._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
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
    val map1 = Map(("a","2"),("z","2"),("c","2"),("d","2"),("e","2"),("f","2"))

    val map2 = Map(("q","A"),("b","b"),("z","b"))

   map1 filterKeys map2.contains foreach{
      println
   }

    case class Config(quorum:String, rootdir:String,clientPort:String)





    val c = Config("slave1,slave2,slave3","/hbase-unsecure","2181")



    val conf = new SparkConf().setMaster("local[2]").setAppName("Test")
    lazy val sparkContext = new SparkContext(conf)
    implicit val config = HBaseConfig(c)
    val sc = new HbaseSC(sparkContext)

    sparkContext.textFile("")
  }


}
