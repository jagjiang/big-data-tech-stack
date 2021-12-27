package com.mintlolly.scala.demo

/**
 * Created on 2021/12/15
 *
 * @author jiangbo
 *         Description:
 */
class PersonScala{
  private[this] var _name: String = _
  private[this] var _age: Int = _

  def name: String = _name

  def name_=(value: String): Unit = {
    _name = value
  }

  def age: Int = _age

  def age_=(value: Int): Unit = {
    _age = value
  }

    def this(name:String,age:Int){
    this()
    this.name = name
    this.age = age
  }
  override def toString: String = "PersonScala{" + "name='" + name + """'""" + ", age=" + age + '}'
}
