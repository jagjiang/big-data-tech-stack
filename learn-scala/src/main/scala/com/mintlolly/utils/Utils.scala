package com.mintlolly.utils

import java.io.FileInputStream
import java.util.Properties

/**
 *
 * @description
 * @date 2019/12/5 15:50 
 * @author jiangbo
 *
 */
object Utils {
  /**
   * 获取配置文件
   *
   * @param proPath
   * @return
   */
  def getProperties(proPath: String) = {
    val properties: Properties = new Properties()
    properties.load(new FileInputStream(proPath))
    properties
  }

  /**
   * 计算两条记录之间的距离(投影坐标系)
   * @param gridx
   * @param gridy
   * @param gridx2
   * @param gridy2
   * @return
   */
  def calcDistance(gridx:String, gridy:String, gridx2:String, gridy2:String): Double ={
    if(gridx.equals(gridx2) && gridy.equals(gridy2)){
      0.0
    }else if(gridx.equals(gridx2)){
      Math.abs(gridy2.toDouble - gridy.toDouble)
    }else if(gridy.equals(gridy2)){
      Math.abs(gridx2.toDouble - gridx.toDouble)
    }else{
      Math.sqrt((gridy2.toDouble - gridy.toDouble)
        * (gridy2.toDouble - gridy.toDouble)
        + (gridx2.toDouble - gridx.toDouble)
        * (gridx2.toDouble - gridx.toDouble))
    }
  }

  /**
   * 计算两条记录之间的距离(经纬度)
   * @param lon
   * @param lat
   * @param lon2
   * @param lat2
   * @return  m 米
   */
  def calcDistanceLonLat(lon:String,lat:String,lon2:String,lat2:String):BigDecimal = {
    if(lat!=0 && lon!=0 && lat2!=0 && lon2!=0){
      val R =  6378137
      val radLat1 = lat.toDouble* Math.PI / 180
      val radLat2 = lat2.toDouble* Math.PI / 180
      val a = radLat1 - radLat2
      val b = lon.toDouble* Math.PI / 180 - lon2.toDouble* Math.PI / 180
      val s = 2 * Math.sin(Math.sqrt(Math.pow(Math.sin(a/2),2) + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)))
      BigDecimal.decimal(s * R).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }else{
      BigDecimal.decimal(0).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }
  }
}
