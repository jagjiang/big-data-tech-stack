package com.mintlolly.scala.demo

/**
 * Create by on jiangbo 2020/7/22 9:23
 *
 * 密封类
 * Description:sealed关键字 可以修饰类和特质 只能在当前文件里被继承
 *
 * 密封类提供一种约束：不能在类定义的文件之外定义任何新的子类
 *
 * 作用：如果子类都明确的情况下，为了防止继承滥用，为抽象类添加sealed
 *
 * 在检查模式匹配的时候，用sealed修饰目的是让scala知道这些case的所有情况，
 * scala就能够在编译的时候进行检查，看你写的代码是否有没有漏掉什么没case到，减少编程的错l误
 * (Warning级别不会报错)
 */
sealed abstract class Drawing

case class Point(x:Int,y:Int) extends Drawing

case class Circle(p:Point,r:Int) extends Drawing

case class Cylinder(c:Circle,h:Int) extends Drawing

sealed trait Color


object SealedDemo{
  def what(d:Drawing):String = d match {
    case Point(_,_) => "点"
    case Circle(_,_) => "圆"
  }
  def main(args: Array[String]): Unit = {
    println(what(Point(1,1)))
  }
}