package com.mintlolly.hbase

/**
 *
 * @description
 * @date 2019/12/12 14:25 
 * @author jiangbo
 *
 */

trait Reads[A] extends Serializable{
  def read(data:Array[Byte]):A
}

trait Writes[A] extends Serializable{
  def write(data:A):Array[Byte]
}

trait Formats[A] extends Reads[A] with Writes[A]
