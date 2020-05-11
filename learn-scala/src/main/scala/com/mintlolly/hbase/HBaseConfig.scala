package com.mintlolly.hbase

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration

/**
 *
 * @description Wrapper for HBaseConfiguration
 * @date 2019/12/11 17:41 
 * @author jiangbo
 *
 */
class HBaseConfig(defaults: Configuration) extends Serializable {
  def get: Configuration = HBaseConfiguration.create(defaults)
}

object HBaseConfig {
  def apply(conf: Configuration): HBaseConfig = new HBaseConfig(conf)

  def apply(options: (String, String)*): HBaseConfig = {
    val conf = HBaseConfiguration.create
    for ((key, value) <- options) {
      conf.set(key, value)
    }
    apply(conf)
  }

  def apply(conf: {def rootdir: String; def quorum: String}): HBaseConfig = apply(
    "hbase.rootdir" -> conf.rootdir,
    "hbase.zookeeper.quorum" -> conf.quorum)

}

