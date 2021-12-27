package com.mintlolly.scala.demo

/**
 * Create by on jiangbo 2020/7/22 10:14
 *
 * Description:
 *
 */
class MatchDemo{
  def matchDemo(x:Char):String = x match {
    case 'a' => "left"
    case 'd' => "right"
    case 'w' => "up"
    case 's' => "down"
  }
}

object MatchDemo{
  def main(args: Array[String]): Unit = {
    val demo = new MatchDemo
    println(demo.matchDemo('a'))
    val array = Array(Array("hello","woad"),Array("world","qaq"),Array("word","name"))
    array.flatten.foreach(println)
  }
}