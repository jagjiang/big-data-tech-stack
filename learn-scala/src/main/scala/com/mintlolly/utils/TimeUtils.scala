package com.mintlolly.utils

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

/**
 *
 * @description 时间工具类
 * @date 2019/12/3 14:23 
 * @author jiangbo
 *
 */
object TimeUtils {
  /**
   * 字符串转时间戳 yyyy-MM-dd HH:mm:ss.SSS
   * @param time
   * @return Long
   */
  def toTimeStamps(time:String):Long ={
    val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val timeStamp = fm.parse(time).getTime
    timeStamp
  }

  /**
   * 13位时间戳转日期
   * @param time
   * @return String yyyy-MM-dd HH:mm:ss.SSS
   */
  def toDate13(time:String):String = {
    val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val date = fm.format(new Date(time.toLong))
    date
  }
  /**
   * 10位时间戳转日期
   * @param time
   * @return String yyyy-MM-dd HH:mm:ss
   */
  def toDate10(time:String):String = {
    val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = fm.format(new Date(time.toLong*1000))
    date
  }

  /**
   * 增加多少天
   * @param yyyyMMdd
   * @param num
   * @return String yyyyMMdd
   */
  def addNDays(yyyyMMdd:String,num:Int):String = {
    val cal:Calendar = Calendar.getInstance()
    val fm = new SimpleDateFormat("yyyyMMdd")
    cal.setTime(fm.parse(yyyyMMdd));//设置起开始时间
    cal.add(Calendar.DATE, num);//增加num天  
    fm.format(new Date(cal.getTime.getTime))
  }

  def main(args: Array[String]): Unit = {
    println(addNDays("20191231",11))
  }

}
