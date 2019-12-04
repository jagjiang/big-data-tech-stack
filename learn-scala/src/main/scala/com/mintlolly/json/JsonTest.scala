package com.mintlolly.json

import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

/**
 *
 * @description
 * @date 2019/12/4 11:23 
 * @author jiangbo
 *
 */
object JsonTest {
  def main(args: Array[String]): Unit = {
    val map1 = Map("longitude"->1163899110,"latitude"->399134070)
    val map2 = Map("longitude"->1163899111,"latitude"->399134070)
    val map3 = Map("longitude"->1163899112,"latitude"->399134070)
    val list = List(map1,map2,map3)
    val json2 =("trajCount" -> "63") ~
      ("subParam" ->list) ~
      ("ceshi" ->"test")
    println(json2)
    print(compact(render(json2)))
  }
}
