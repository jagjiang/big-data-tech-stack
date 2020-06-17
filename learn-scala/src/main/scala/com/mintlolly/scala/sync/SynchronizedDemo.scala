package com.mintlolly.scala.sync

/**
 * Create by on jiangbo 2020/6/12 13:48
 *
 * Description:并发编程中的synchronized
 *
 */
object SynchronizedDemo {
  //用于代码块
  synchronized{
  }

  //用于方法
  def func():Unit = this.synchronized{
  }

}
