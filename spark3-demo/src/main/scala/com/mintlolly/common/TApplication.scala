package com.mintlolly.common


import com.mintlolly.util.EnvUtil
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}


/**
 * Create by on jiangbo 2021/1/3 21:17
 *
 * Description:
 *
 */
trait TApplication {
  //控制抽象
  def start(appName:String ="Application")(op : => Unit): Unit ={
    val sparkConf = new SparkConf().setAppName(appName)
    if(null!= EnvUtil.getSystemEnv()){
      sparkConf.setMaster(EnvUtil.getSystemEnv())
    }
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    //设置环境信息
    EnvUtil.put(spark)
    try{
      op
    }catch {
      case ex: Exception => println(ex.getMessage)
    }
    spark.stop()
    //清除环境信息
    EnvUtil.clear()
  }
}
