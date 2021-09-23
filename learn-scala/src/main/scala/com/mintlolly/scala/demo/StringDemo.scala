package com.mintlolly.scala.demo

/**
 * Created on 2021/9/22
 *
 * @author jiangbo
 *         Description:
 */
object StringDemo {
  def main(args: Array[String]): Unit = {
    //一个双引号 原生字符串中的转义字符会被转义
    println("hello\tworld")
    //三个双引号 原生字符串中的转义字符不会被转义
    println("""hello\tworld""")
  }
}
