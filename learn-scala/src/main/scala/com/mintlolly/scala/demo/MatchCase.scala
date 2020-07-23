package com.mintlolly.scala.demo

/**
 * Create by on jiangbo 2020/7/22 10:14
 *
 * Description:
 *
 */
class MatchDemo extends App {
  def matchDemo(x:Char):String = x match {
    case 'a' => "left"
    case 'd' => "right"
    case 'w' => "up"
    case 's' => "down"
  }
}
object MatchCase{
  def main(args: Array[String]): Unit = {
    val matchdemo = new MatchDemo
    println(matchdemo.matchDemo('a'))
  }
}