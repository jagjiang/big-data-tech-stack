package com.mintlolly.scala.demo

/**
 * Create by on jiangbo 2021/1/11 13:57
 *
 * Description:包的可见性
 * 包访问权限（表示属性有了限制。同时包也有了限制）
 * 体现出Scala包使用的灵活性
 *
 */
//伴生类
class PackageAuth {
  //增加包访问权限后
  // 1.private同时起作用。不仅同类可以使用，
  // 2.同时com.mintlolly包下其他类也可以使用
  private[mintlolly] val name = "hello world"
}

//伴生对象
object PackageAuth {
  def main(args: Array[String]): Unit = {
    val auth = new PackageAuth()
    println(auth.name)
  }
}