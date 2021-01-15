package com.mintlolly.service

import com.mintlolly.bean.JsonBean.WordCountBean
import com.mintlolly.common.TService
import com.mintlolly.dao.WordCountDao
import com.mintlolly.service.util.AnalysisUtil
import com.mintlolly.util.{EnvUtil, JsonUtil}
import org.apache.spark.sql.SparkSession


/**
 * Create by on jiangbo 2021/1/3 21:06
 *
 * Description:WordCount主要算法处理逻辑
 *
 */
class WordCountService extends TService{
  implicit val formats = org.json4s.DefaultFormats
  private val spark: SparkSession = EnvUtil.get()
  import spark.implicits._

  private val wordCountDao = new WordCountDao()
  def dataAnalysis(json: String)={

    val wordCountBean = JsonUtil.Json2CaseClass[WordCountBean](json)
    val inPath = wordCountBean.path
    val outPath = wordCountBean.output

    val lines = wordCountDao.readFile(inPath)

    val words = AnalysisUtil.wordCount(lines)

    val df = words.toDF("word", "num").createOrReplaceTempView("words")
    spark.sql("select * from words").show
    wordCountDao.saveFile(words,outPath)
  }
}
