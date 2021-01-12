package com.mintlolly.service

import com.mintlolly.bean.JsonBean.WordCountBean
import com.mintlolly.common.TService
import com.mintlolly.dao.WordCountDao
import com.mintlolly.service.util.AnalysisUtil
import com.mintlolly.util.JsonUtil


/**
 * Create by on jiangbo 2021/1/3 21:06
 *
 * Description:WordCount主要算法处理逻辑
 *
 */
class WordCountService extends TService{
  implicit val formats = org.json4s.DefaultFormats

  private val wordCountDao = new WordCountDao()
  def dataAnalysis(json: String)={

    val wordCountBean = JsonUtil.Json2CaseClass[WordCountBean](json)
    val inPath = wordCountBean.path
    val outPath = wordCountBean.output

    val lines = wordCountDao.readFile(inPath)

    val words = AnalysisUtil.wordCount(lines)
    wordCountDao.saveFile(words,outPath)
  }
}
