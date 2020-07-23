package com.mintlolly.scala.demo

/**
 * Create by on jiangbo 2020/7/22 9:10
 *
 * Description:
 *
 */
object EnumDemo extends Enumeration {

  //type相当于声明一个类型的别名
  //这里仅仅是为了将EnumDemo的类型暴露出来给外界使用
  type EnumDemo = Value

  val NET,MEMORY,DISK = Value
  val OTHER = Value(-1,"other source")

  def loadData(enumDemo: EnumDemo):Unit = {
    enumDemo match {
      case NET => println ("source type is " + enumDemo)
      case MEMORY => println ("source type is " + enumDemo)
      case DISK => println ("source type is " + enumDemo)
      case _ => println ("unknown type")
    }
  }

  def main(args: Array[String]): Unit = {
    val memory = EnumDemo(1)
    loadData(memory)
    println()
    for(e <- EnumDemo.values){
      println(e.id+":"+e)
    }

  }
}
