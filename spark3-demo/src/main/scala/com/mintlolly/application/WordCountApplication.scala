package com.mintlolly.application

import com.mintlolly.common.TApplication
import com.mintlolly.controller.WordCountController


/**
 * Create by on jiangbo 2021/1/3 21:05
 *
 * Description:调用层
 *
 */
object WordCountApplication extends TApplication{
  def main(args: Array[String]): Unit = {
    val params = args(0)
    start("WordCount"){
      val wordCountController = new WordCountController()
      wordCountController.dispatch(params)
    }
  }



}
