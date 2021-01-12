package com.mintlolly.util

import org.apache.commons.lang3.SystemUtils
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

/**
 * Create by on jiangbo 2021/1/3 21:02
 *
 * Description:环境信息
 *
 */
object EnvUtil {

  private val scLocal = new ThreadLocal[SparkSession]()

  def put(sc:SparkSession): Unit ={
    scLocal.set(sc)
  }

  def get(): SparkSession ={
    scLocal.get()
  }

  def clear(): Unit ={
    scLocal.remove()
  }

  def getSystemEnv()={
    if(SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_WINDOWS){
      "local[*]"
    }else{
      null
    }
  }
}
