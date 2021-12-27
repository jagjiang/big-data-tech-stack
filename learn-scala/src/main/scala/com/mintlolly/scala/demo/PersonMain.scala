package com.mintlolly.scala.demo

import scala.collection.mutable.ListBuffer

/**
 * Created on 2021/12/15
 *
 * @author jiangbo
 *         Description:
 */
object PersonMain {
  def main(args: Array[String]): Unit = {
    val personJava = new PersonJava
    personJava.setName("java")
    personJava.setAge(12)
    personJava.sex = "男"
    println(s"personJava = ${personJava.toString}")

    //    val personScala = new PersonScala("scala",12)
    val personScala = new PersonScala
    personScala.age = 12
    personScala.name = "scala"
    println(s"personScala = ${personScala.toString}")

    var more = 10

    def addMore = (x: Int) => {
      more = 10 + 10
      x + more
    }

    println(addMore(12))
    println(more)
    //高阶函数
    val square = (x: Int) => {
      x * x
    }
    val multi = (fun: Int => Int, x: Int) => {
      fun(x) * x
    }
    for (i <- 1 to 10) {
      i match {
        case i : Int => println("数值型")
        case 2 => println("two")
        case 4 => println("four")
        case _ => println("others")
      }
    }
    println(multi(square, 10))
    //柯里化
    println(curriedSum(2)(4))

    //10 个整数的数组，所有元素初始化为 0
    val ints = new Array[Int](10)
    //使用指定值初始化，此时不需要 new 关键字
    val ints1 = Array[Int](10,120)
    ints.foreach(print(_))
    println
    println("------------------------------------------------")
    //List 是不可变的,无法修改list内的元素
    var ints2 = List(1, 2, 3, 4)
//    ints2 += 1
    ints2 = ints2.+:(0)
    ints2 = ints2.:+(6)
//    ints2(2) = 22  //错误的
    ints2.foreach(print)
    println
    println("------------------------------------------------")
    //ListBuffer 可以修改list内的元素
    val buffer = new ListBuffer[Int]
    //尾部追加元素
    buffer += 1

    //头部追加元素
    0 +=: buffer
    buffer(0) = 10
    buffer.foreach(print)
    println
    println("------------------------------------------------")
  }

  def curriedSum(x: Int)(y: Int) = x * y
}
