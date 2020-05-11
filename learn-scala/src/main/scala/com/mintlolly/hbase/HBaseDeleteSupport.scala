package com.mintlolly.hbase


import org.apache.hadoop.hbase.client.Delete
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.spark.rdd.RDD

import com.mintlolly.hbase.HBaseDeleteMethods._

/**
 * Adds implicit methods to
 * `RDD[K]`,
 * `RDD[(K, Set[Q])]`,
 * `RDD[(K, Set[(Q, Long)])]`,
 * `RDD[(K, Map[String, Set[Q]])]`,
 * `RDD[(K, Map[String, Set[(Q, Long)]])]`,
 * to delete HBase tables.
 */
trait HBaseDeleteSupport {
  implicit def deleteHBaseRDDKey[K: Writes](rdd: RDD[K]): HBaseDeleteRDDKey[K] = {
    new HBaseDeleteRDDKey(rdd)
  }

  implicit def deleteHBaseRDDSimple[K: Writes, Q: Writes](rdd: RDD[(K, Set[Q])]): HBaseDeleteRDDSimple[K, Q] = {
    new HBaseDeleteRDDSimple(rdd, del[Q])
  }

  implicit def deleteHBaseRDDSimpleT[K: Writes, Q: Writes](rdd: RDD[(K, Set[(Q, Long)])]): HBaseDeleteRDDSimple[K, (Q, Long)] = {
    new HBaseDeleteRDDSimple(rdd, delT[Q])
  }

  implicit def deleteHBaseRDD[K: Writes, Q: Writes](rdd: RDD[(K, Map[String, Set[Q]])]): HBaseDeleteRDD[K, Q] = {
    new HBaseDeleteRDD(rdd, del[Q])
  }

  implicit def deleteHBaseRDDT[K: Writes, Q: Writes](rdd: RDD[(K, Map[String, Set[(Q, Long)]])]): HBaseDeleteRDD[K, (Q, Long)] = {
    new HBaseDeleteRDD(rdd, delT[Q])
  }
}

private[hbase] object HBaseDeleteMethods{
  type Deleter[Q] = (Delete, Array[Byte], Q) => Delete

  def del[Q](delete:Delete,cf: Array[Byte], q: Q)(implicit wq: Writes[Q]): Delete ={
    delete.addColumn(cf, wq.write(q))
  }

  def delT[Q](delete: Delete, cf: Array[Byte], qt: (Q,Long))(implicit wq:Writes[Q]): Delete = {
    delete.addColumn(cf,wq.write(qt._1),qt._2)
  }
}

sealed abstract class HBaseDeleteHelpers{
  protected def convert[K, Q](id: K, values: Map[String, Set[Q]], del: Deleter[Q])(implicit wk:Writes[K], ws: Writes[String]): Option[(ImmutableBytesWritable,Delete)] = {
    val d = new Delete(wk.write(id))
    var empty = true
    //for 循环还可以这样使用
    for{
      (family,contents) <-values
      fb = ws.write(family)
      content <- contents
    }{
      empty = false
      del(d, fb, content)
    }
    if(empty) None else Some(new ImmutableBytesWritable,d)
  }

  protected def convert[K](id:K, families: Set[String])(implicit wk: Writes[K], ws:Writes[String]):Option[(ImmutableBytesWritable,Delete)] ={
    val d = new Delete(wk.write(id))
    for(family <- families){
      d.addFamily(ws.write(family))
    }
    Some(new ImmutableBytesWritable, d)
  }

  protected def convert[K](id: K)(implicit wk: Writes[K]):Option[(ImmutableBytesWritable, Delete)] ={
    val d = new Delete(wk.write(id))
    Some(new ImmutableBytesWritable, d)
  }
}
final class HBaseDeleteRDDKey[K:Writes](rdd: RDD[K]) extends HBaseDeleteHelpers with Serializable {
  /**
   * Delete rows specified by rowkeys of the underlying RDD from HBase
   * @param table
   * @param config
   */
  def deleteHBase(table:String)(implicit config:HBaseConfig): Unit ={
    val conf = config.get
    val job = createJob(table,conf)
    rdd.flatMap({
      k =>convert(k)
    }).saveAsNewAPIHadoopDataset(job.getConfiguration)
  }

  def deleteHBase(table:String, families:Set[String])(implicit config:HBaseConfig):Unit = {
    val conf = config.get
    val job = createJob(table,conf)
    rdd.flatMap({
      k => convert(k,families)
    }).saveAsNewAPIHadoopDataset(job.getConfiguration)
  }

  def deleteHBase[Q:Writes](table:String, family:String, columns: Set[Q])(implicit config:HBaseConfig):Unit = {
    val conf = config.get
    val job = createJob(table,conf)

    rdd.flatMap({
      k => convert(k,Map(family -> columns), del[Q])
    }).saveAsNewAPIHadoopDataset(job.getConfiguration)
  }

  def deleteHBase[Q:Writes](table:String,qualifiers: Map[String,Set[Q]])(implicit config:HBaseConfig): Unit ={
    val conf = config.get
    val job = createJob(table,conf)

    rdd.flatMap({
      k => convert(k, qualifiers, del[Q])
    }).saveAsNewAPIHadoopDataset(job.getConfiguration)
  }
}

final class HBaseDeleteRDDSimple[K:Writes, Q](val rdd: RDD[(K, Set[Q])], del: Deleter[Q]) extends HBaseDeleteHelpers with Serializable {
  def deleteHBase(table: String, family: String)(implicit config: HBaseConfig):Unit ={
    val conf = config.get
    val job = createJob(table, conf)

    rdd.flatMap({
      case (k, v) => convert(k, Map(family -> v), del)
    }).saveAsNewAPIHadoopDataset(job.getConfiguration)
  }
}

final class HBaseDeleteRDD[K:Writes, Q](val rdd: RDD[(K, Map[String, Set[Q]])], val del: Deleter[Q]) extends HBaseDeleteHelpers with Serializable{
  def deleteHBase(table: String)(implicit config: HBaseConfig): Unit ={
    val conf = config.get
    val job = createJob(table, conf)

    rdd.flatMap({
      case(k, v) => convert(k, v, del)
    }).saveAsNewAPIHadoopDataset(job.getConfiguration)
  }
}
