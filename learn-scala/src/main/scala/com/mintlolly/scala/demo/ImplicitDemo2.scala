package com.mintlolly.scala.demo


import scala.language.implicitConversions

import com.mintlolly.scala.demo.Convert.person2Thor

/**
 * Created on 2021/12/18
 *
 * @author jiangbo
 *         Description:
 */
class ImplicitDemo2 {
}
object ImplicitDemo2 extends App {
  new Person("people")
//  implicit def person2Thor(person: Person):Thor = new Thor(person.name)
  new Person("thor").hammer()

  def formatted(context: String)(implicit person:Person):Unit={
    println(context + person.name)
  }
  implicit val bianliang = new Person("wang")
  formatted("name")

  def formatted2(implicit person:Person,context: String):Unit={
    println(context + person.name)
  }
  formatted2(new Person("jj"),"name")
  //不存在这种写法，语法错误
//  def formatted3(context: String,implicit person:Person):Unit={
//    println(context + person.name)
//  }
}
class Person(val name:String){

}
class Thor(name:String){
  def hammer():Unit ={
    println(s"${name}举起了雷神之锤")
  }
}

//需要静态化，一开始就要加载，才能被调用
object Convert{
  implicit def person2Thor(person: Person):Thor = new Thor(person.name)
}