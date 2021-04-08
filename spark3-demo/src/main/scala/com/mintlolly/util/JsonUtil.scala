package com.mintlolly.util

import org.json4s.jackson.JsonMethods
import org.json4s.{Extraction, Formats, JsonInput}
import scala.reflect.Manifest
/**
 * Create by on jiangbo 2021/1/4 9:53
 *
 * Description:case class与Json之间的转换，方便数据操作
 *
 */
object JsonUtil {

  implicit val formats = org.json4s.DefaultFormats
  def Json2CaseClass[A](json: JsonInput)(implicit formats: Formats, mf: Manifest[A]): A =
    JsonMethods.parse(json, formats.wantsBigDecimal, formats.wantsBigInt).extract(formats, mf)

  def CaseClass2Json[A <: AnyRef](a: A)(implicit formats: Formats): String =
    JsonMethods.mapper.writeValueAsString(Extraction.decompose(a)(formats))

}
