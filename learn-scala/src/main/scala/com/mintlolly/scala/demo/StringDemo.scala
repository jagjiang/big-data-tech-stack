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
    //符号字面量写法为： '标识符,这里标识符可以是任何字母或者数字的组合
    //符号字面量会被映射为scala.Symbol的实例
    //如:符号字面量 'x 会被编译器翻译为 scala.Symbol("x")。符号字面量可选方法很少，只能通过 .name 获取其名称
    val symbol = 'hello
    println(s"symbol.name = ${symbol.name}")
    println(symbol)
    //插值表达式
    val name = "xiaoming"
    println(s"My name is $name,I'm ${2*4}.");
    val a = for(n <- 1 to 10;i<- 1 until 10 ; if  n!=i) {
      println(s"n = ${n},i=${i}")
      n * i
    }
    println(s"a = ${a}")
    //yield关键字从for循环中产生vector，这称为for推导式
    val fy = for(n <- 1 to 10;i<- 1 until 10 ; if  n!=i) yield (n*i)
    println(s"fy = ${fy}")

    //遍历
    println("========================================================")
    val b = Array("a","b","c","d","b")
    for(elem <- b){
      println(s"elem = ${elem}")
    }
    for(index <- b.indices){
      println(s"b(${index}) = ${b(index)}")
    }
    //反向遍历
    for(index <-b.indices.reverse){
      println(s"b(${index}) = ${b(index)}")
    }
    val num = b count (_ == "b")
    println(s"num = ${num}")
    val list = List(1,2,3,4,5,-1,-3)
    println(list span(_ > 0))
    val tuples = b zip list
    tuples.foreach(println)
  }
}
