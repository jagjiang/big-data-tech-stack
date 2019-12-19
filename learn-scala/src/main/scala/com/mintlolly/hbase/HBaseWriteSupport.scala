package com.mintlolly.hbase

import com.mintlolly.hbase.HBaseWriteMethods._
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.spark.rdd.RDD


/**
 * Adds implicit methods to
 * `RDD[(K, Map[Q, V])]`,
 * `RDD[(K, Seq[V])]`,
 * `RDD[(K, Map[String, Map[Q, V]])]`
 * `RDD[(K, Map[Q, (V, Long)])]`,
 * `RDD[(K, Seq[(V, Long)])]`,
 * `RDD[(K, Map[String, Map[Q, (V, Long)]])]`
 * to write to HBase tables.
 */
trait HBaseWriteSupport {
  implicit def toHBaseRDDSimple[K: Writes, Q: Writes, V: Writes](rdd: RDD[(K, Map[Q, V])]): HBaseWriteRDDSimple[K, Q, V] = {
    new HBaseWriteRDDSimple(rdd, pa[V])
  }

  implicit def toHBaseRDDSimpleTS[K: Writes, Q: Writes, V: Writes](rdd: RDD[(K, Map[Q, (V, Long)])]): HBaseWriteRDDSimple[K, Q, (V, Long)] = {
      new HBaseWriteRDDSimple(rdd, pa[V])
  }

  implicit def toHBaseRDDFixed[K: Writes, V: Writes](rdd: RDD[(K, Seq[V])]): HBaseWriteRDDFixed[K, V] = {
      new HBaseWriteRDDFixed(rdd, pa[V])
  }

  implicit def toHBaseRDDFixedTS[K: Writes, V: Writes](rdd: RDD[(K, Seq[(V, Long)])]): HBaseWriteRDDFixed[K, (V, Long)] = {
      new HBaseWriteRDDFixed(rdd, pa[V])
  }

  implicit def toHBaseRDD[K: Writes, Q: Writes, V: Writes](rdd: RDD[(K, Map[String, Map[Q, V]])]): HBaseWriteRDD[K, Q, V] = {
      new HBaseWriteRDD(rdd, pa[V])
  }

  implicit def toHBaseRDDT[K: Writes, Q: Writes, V: Writes](rdd: RDD[(K, Map[String, Map[Q, (V, Long)]])]): HBaseWriteRDD[K, Q, (V, Long)] = {
      new HBaseWriteRDD(rdd, pa[V])
  }
}

private[hbase] object HBaseWriteMethods{
  type PutAdder[V] = (Put,Array[Byte],Array[Byte], V) =>Put

  def pa[V](put: Put, cf: Array[Byte], q:Array[Byte], v:V)(implicit wv: Writes[V]):Put ={
    put.addColumn(cf, q, wv.write(v))
  }

  def pa[V](put:Put, cf: Array[Byte], q:Array[Byte], v:(V, Long))(implicit wv: Writes[V]): Put = {
    put.addColumn(cf, q, v._2, wv.write(v._1))
  }
}

abstract class HBaseWriteHelpers {
  protected def convert[K,Q,V](id: K, values: Map[String,Map[Q, V]],put:PutAdder[V](implicit wk: Writes[K], wq: Writes[Q], ws:Writes[String]):Option[(ImmutableBytesWritable,Put)] = {
    val p = new Put(wk.write(id))
    var empty = true
    for {
      (family,content) <- values
      fb = ws.write(family)
      (qualifier,value) <- content
    }{
      empty = false
      //与前面type PutAdder[V]关联
      put(p, fb, wq.write(qualifier), value)
    }

    if(empty) None else Some(new ImmutableBytesWritable, p)
  }
}


final class HBaseWriteRDDSimple[K: Writes, Q: Writes,V](rdd:RDD[(K, Map[Q, V])],put:PutAdder[V]) extends HBaseWriteHelpers with Serializable{
  def toHBase(table:String, family: String)(implicit config: HBaseConfig):Unit = {
    val conf = config.get
    val job = createJob(table,conf)

    rdd.flatMap({ case (k,v) => convert(k,Map(family -> v),put)})
      .saveAsNewAPIHadoopDataset(job.getConfiguration)
  }
}

final class HBaseWriteRDDFixed[K: Writes, V](rdd: RDD[(K,Seq[V])], put: PutAdder[V]) extends HBaseWriteHelpers with Serializable{
  def toHBase[Q:Writes](table:String,family:String,headers:Seq[Q])(implicit config:HBaseConfig):Unit ={
    val conf = config.get
    val job = createJob(table,conf)

    val sc = rdd.context
    val bheaders = sc.broadcast(headers)
    //这是什么意思
    rdd.flatMap({case(k,v) => convert(k,Map(family->Map(bheaders.value zip v:_*)),put)})
      .saveAsNewAPIHadoopDataset(job.getConfiguration)
  }
}

final class HBaseWriteRDD[K: Writes, Q: Writes, V](rdd:RDD[(K, Map[String,Map[Q,V]])],put:PutAdder[V]) extends HBaseWriteHelpers with Serializable{
  def toHBase(table: String)(implicit config: HBaseConfig): Unit = {
    val conf = config.get
    val job = createJob(table,conf)

    rdd.flatMap({case (k,v) => convert(k, v, put)})
      .saveAsNewAPIHadoopDataset(job.getConfiguration)
  }


}