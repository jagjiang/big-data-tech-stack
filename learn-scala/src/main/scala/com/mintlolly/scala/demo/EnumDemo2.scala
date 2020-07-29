package com.mintlolly.scala.demo

/**
 * Create by on jiangbo 2020/7/22 9:59
 *
 * Description:
 *
 */
class EnumDemo2 extends Enumeration {
  val MON,TUE,WED = Value
  val OTHER = Value(-1,"others")
}

object TypeTest {
  def weekDay:Unit = {
    val en = new EnumDemo2
    for(i <- en.values){
      println(i.id+":"+i)
    }
  }

  def main(args: Array[String]): Unit = {
    weekDay
  }
}
