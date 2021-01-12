package com.mintlolly.util

import java.util

import org.yaml.snakeyaml.Yaml


/**
 * Create by on jiangbo 2021/1/5 11:12
 *
 * Description:
 *
 */
object Utils {
  def getProperties(fileName:String,props:String*)={
    val stream = getClass.getResourceAsStream(fileName)
    val yaml = new Yaml()
    var obj = yaml.load(stream).asInstanceOf[util.HashMap[String, Object]]
    var value:String =""
    val properties = props.map(prop =>{
      try{
        obj = obj.get(prop).asInstanceOf[util.HashMap[String, Object]]
      }catch {
        case ex:ClassCastException => value = obj.get(prop).asInstanceOf[String]
      }
    })
    if(!value.isEmpty){
      value
    }else{
      obj
    }
  }
}
