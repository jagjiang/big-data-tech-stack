package com.mintlolly.scala.demo

import scala.reflect.ClassTag

/**
 * Create by on jiangbo 2020/12/28 16:06
 *
 * Description:
 *
 */
object MethodDemo {
  def main(args: Array[String]): Unit = {
    println(test("最简单的函数"))
    val ints = List(12, 22, 33, 44, 55)
  }

  def test(string: String):String ={
    string
  }


}
