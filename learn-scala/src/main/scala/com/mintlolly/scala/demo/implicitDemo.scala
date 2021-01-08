package com.mintlolly.scala.demo

/**
 * Create by on jiangbo 2021/1/8 13:21
 *
 * Description:隐式转换的注意事项和细节
 * 1)函数名可以是任意的，隐式转换与函数名称无关，只与函数签名有关
 * 函数签名(函数参数类型和返回类型)
 * 2)隐式函数可以有多个,但是需要保证在当前环境下只有一个隐式函数能被识别
 */
//========================================定义一些类=======================================================
/**
 * 隐式转换丰富类库功能
 *
 */

class Mysql {
  def insert(int: Int) = {
    println(s"Mysql insert int = $int")
  }
}

class DB {
  def insert(int: Int) = {
    println(s"DB insert int = $int")
  }

  def delete(int: Int) = {
    println(s"DB delete int = $int")
  }
}


//implicit class DB2()   //b)所以不能这么定义

//========================================定义一些类=======================================================

object implicitDemo {

  def main(args: Array[String]): Unit = {
    implicit def a(double: Double) = double.toInt

    // 帮助理解 2)
    // implicit def b(double: Double)  = double.toInt
    implicit def f(float: Float) = float.toInt

    val num: Int = 1.1
    val num2: Int = 1.1f

    /**
     * 隐式转换丰富类库,通过这样操作直接就可以把DB的方法放到Mysql类中
     */
    implicit def addDel(mysql: Mysql) = {
      new DB
    }

    val mysql = new Mysql()
    mysql.insert(num)
    //妙啊
    mysql.delete(num2)

    /**
     * 隐式值也叫隐身变量，将某个形参变量标记为implicit,
     * 编译器会在方法省略隐式参数的情况下去搜索作用域内的隐式值作为缺省参数
     * 和变量名无关 多个同一类型的隐式变量会报错
     */
    implicit val name1 = "老王"
    //  implicit val name2 = "小王"

    def hello(implicit name: String) = {
      println(s"hello $name")
    }

    //隐式比默认优先级高 所以输出老王
    def hate(implicit name: String = "如果有默认值呢") = {
      println(s"hate $name")
    }

    //类型不匹配报错
    def like(implicit name: Int) = {
      println(s"hate $name")
    }

    hello
    hate
    //  like

    //隐式类 ====================================================================
    /**
     * 隐式类的特点
     * a)其所在的构造参数有且只能有一个
     * b)隐式类必须被定义在“类”或“伴生对象”或“包对象”里，即隐式类不能是顶级的
     * implicit' modifier cannot be used for top-level objects
     * c)隐式类不能使样本类（case class)
     * d)作用域内不能有与之相同名称的标识符
     * DEMO
     */

    /**
     * 对应 a)点
     * implicit class must have a primary constructor with exactly one argument in first parameter list
     * 隐式类必须具有一个主构造函数，该构造函数的第一个参数列表中只有一个参数
     */
    //implicit class DB2() //报错
    //implicit class DB2(mysql: Mysql,int: Int)
    implicit class DB2(mysql: Mysql) {
      def addSelect(id: String) = {
        println(id)
      }
    }

    //只有调用的时候才会扫描隐式类
    mysql.addSelect("乱七八糟的id值")

    // warning 隐式转换不能嵌套使用
    implicit def f1(string: String) = {
      string.toInt
      //      val num:Int = "2"
    }
  }
}


