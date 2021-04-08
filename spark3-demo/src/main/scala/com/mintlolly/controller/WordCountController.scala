package com.mintlolly.controller

import com.mintlolly.common.TController
import com.mintlolly.service.WordCountService


/**
 * Create by on jiangbo 2021/1/3 21:05
 *
 * Description:控制层
 *
 */
class WordCountController extends TController{
  private val wordCountService = new WordCountService()

  def dispatch(json: String): Unit ={
    wordCountService.dataAnalysis(json)
  }
}
