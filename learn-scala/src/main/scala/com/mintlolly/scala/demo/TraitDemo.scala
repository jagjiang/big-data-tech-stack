package com.mintlolly.scala.demo

/**
 * Create by on jiangbo 2021/1/7 15:49
 *
 * Description:
 *
 */
object TraitDemo {
  def main(args: Array[String]): Unit = {

    /**
     * 构建从左到右调过了就不会再调 所以dog不会掉pet animal
     * people  cat -> pet ->animal  dog
     */
    val people = new People with Cat with Dog {
      override def test(): Unit = {
        println("特质相当于接口,继承的类一定要实现特质的方法")
      }
    }

    println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
    /**
     * 特质中变量
     */
    println(people.petName)
    
    println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++")

    /**
     * 1)从右向左开始执行
     * 2)当执行super时，是指左边的特质
     * 3)如果左边没有特质了，则super就是父特质
     * 喂小狗->喂小猫（喂小猫里又有super)->喂宠物
     */
    people.feed()
  }

}

/**
 * 继承特质的类要实现类中的方法
 * 富接口 既有抽象方法、又有非抽象方法
 */
trait Animal{
  println("动物")
  def feed(): Unit ={
    println("喂动物")
  }

  def test()
}

trait Pet extends Animal{
  println("宠物继承自动物")
  override def feed(): Unit ={
    println("喂宠物")
  }
}
trait Cat extends Pet{
  var petName = "kitty"
  println("小猫继承自宠物")
  override def feed(): Unit ={
    println("喂小猫")
    super.feed()
  }
}
trait Dog extends Pet{
  println("小狗继承自宠物")
  override def feed(): Unit ={
    println("喂小狗")
    super.feed()
    //直接指定调用哪个,必须是当前特质的直接父类  Animal执行时报错
    super[Pet].feed()
  }
}

class People {
  println("人喜欢动物")
}
