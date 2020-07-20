package com.mintlolly.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class HBaseOperation {

    // 声明静态配置
    static Configuration conf;
    static Connection conn;
    static Admin admin;

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "slave1,slave2,slave3");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        //hdp的znode parent默认为/hbase-unsecure
        conf.set("zookeeper.znode.parent", "/hbase-unsecure");
        try {
            conn = ConnectionFactory.createConnection(conf);
            admin = conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //判断表是否存在
    public static void isTableExist(String tableName) throws IOException {
        if (admin.tableExists(TableName.valueOf(tableName))) {
            System.out.println(tableName + "表已经存在");
        } else {
            System.out.println(tableName + "表不存在");
        }


    }

    //创建表
    public static void createTable(String tableName, String... cfs) {

        try{
            if(!admin.tableExists(TableName.valueOf(tableName))) {
                //表描述器构造器
                TableDescriptorBuilder  tdb  =TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName))  ;
                //列族描述器构造器
                ColumnFamilyDescriptorBuilder cdb;
                //获得列描述器
                ColumnFamilyDescriptor  cfd;
                for (String columnFamily:cfs) {
                    cdb =  ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily));
                    cdb.setMaxVersions(3);
                    cfd = cdb.build();
                    //添加列族
                    tdb.setColumnFamily(cfd);
                }
                //获得表描述器

                TableDescriptor td = tdb.build();
                //对创建的表进行分区  Bytes.toBytes本身返回就是一个数组
                byte[][] splitKeys = new byte[][]{ Bytes.toBytes("10000"),
                        Bytes.toBytes("20000"), Bytes.toBytes("30000"),
                        Bytes.toBytes("40000") };
                //创建表
                admin.createTable(td,splitKeys);
            }else {
                System.out.println("表已存在！");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //删除表
    public static void dropTable(String tableName) {
        try {
            //禁用表
            admin.disableTable(TableName.valueOf(tableName));
            //删除表
            admin.deleteTable(TableName.valueOf(tableName));
            System.out.println("删除成功");
        } catch (TableNotFoundException e) {
            System.out.println(tableName+"表不存在");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //插入数据  //conn连接比较重所以完成操作后要进行关闭
    public static void insertData(String tableName,String rowkey,String cf,String qualifier,String value){
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(rowkey));
            put.addColumn(Bytes.toBytes(cf),Bytes.toBytes(qualifier),Bytes.toBytes(value));
            table.put(put);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("数据插入成功");
    }
    //查询一条数据
    public static void getResult(String tableName,String rowkey){
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowkey));
            //获取最多的版本
            get.readAllVersions();
            Result result = table.get(get);
            System.out.println(result);
            System.out.println(Bytes.toString(result.getValue(Bytes.toBytes("info"),Bytes.toBytes("name"))));
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //构造过滤器
    public static Filter contructFilter(){
        return new RowFilter(CompareOperator.GREATER,new BinaryComparator(Bytes.toBytes("1001")));
    }

    //扫描hbase中的数据
    public static void scanData(String tableName,Filter filter) throws IOException{
            Table table = conn.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            scan.setFilter(filter);
            ResultScanner scanner = table.getScanner(scan);
            Iterator<Result>  iterator= scanner.iterator();
            LinkedList<String> rowkeys = new LinkedList<>();
            while (iterator.hasNext()){
                Result result = iterator.next();
                String rowkey = Bytes.toString(result.getRow());
                rowkeys.add(rowkey);
            }
            System.out.println(rowkeys);
            scanner.close();
            table.close();
    }
    //关闭资源
    public static void close() throws IOException {
        conn.close();
    }

    public static void main(String[] args) throws IOException {
//        dropTable("test20200508");
//        createTable("test20200508", "info", "info2");
//        insertData("test20200508","1001","info","name","liang");
//        isTableExist("test20200508");
//        getResult("test20200508","1001");

        scanData("test20200508",contructFilter());
    }
}
