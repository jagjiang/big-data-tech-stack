package samples

import java.util

import com.mintlolly.util.{JsonUtil, Utils}
import org.junit.Test

/**
 * Create by on jiangbo 2021/1/4 10:11
 *
 * Description:JsonUtil测试类
 *
 */
class UtilTest {
  case class Class(_name:String, students: List[Student])
  case class Student(sid:String, _name:String)

  @Test
  def testJsonUtil(): Unit ={
    implicit val formats = org.json4s.DefaultFormats
    val s = "{\"_name\":\"Class1\",\"students\":[{\"sid\":\"1\",\"_name\":\"小明\"},{\"sid\":\"1\",\"_name\":\"小王\"}]}"

    val clazz:Class = JsonUtil.Json2CaseClass[Class](s)
    println(clazz)
    println(JsonUtil.CaseClass2Json(clazz))
  }

  @Test
  def testgetProgerties()={
    val properties = Utils.getProperties("/properties.yml", "db", "mysql")

    //如果还是kv形式使用下面方法
    //方法一:转换为map,获取key
    val propkv = properties.asInstanceOf[util.HashMap[String,String]]
    println(propkv.get("database"))
    //TODO 方法二:转换成bean，方便调用
  }
}
