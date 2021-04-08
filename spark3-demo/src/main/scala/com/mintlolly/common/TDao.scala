package com.mintlolly.common

import com.mintlolly.util.EnvUtil
import org.apache.spark.rdd.RDD
/**
 * Create by on jiangbo 2021/1/3 21:31
 *
 * Description:这里的方法基本都是通用的所以直接进行实现
 *
 */
trait TDao {
  def readFile(path:String)={
    val sc = EnvUtil.get().sparkContext
    sc.textFile(path)
  }

  def saveFile[T](rdd:RDD[T],path:String)={
    rdd.saveAsTextFile(path)
  }
}
