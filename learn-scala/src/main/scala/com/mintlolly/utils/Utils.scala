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
  def getProPerties(proPath: String) = {
    val properties: Properties = new Properties()
    properties.load(new FileInputStream(proPath))
    properties
  }
}
