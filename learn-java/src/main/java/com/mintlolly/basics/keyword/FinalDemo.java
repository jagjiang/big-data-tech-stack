package com.mintlolly.basics.keyword;

/**
 * Created on 2021/12/3
 *
 * @author jiangbo
 * Description:
 */
public class FinalDemo {
    public FinalDemo(String a,String b){

    }
    static final int a = 6;

    public static void main(String[] args) {
        //final修饰的变量无法被修改
//        a = 8;
    }
}

final class FF{

}
//final 修饰的类不能派生子类
//class EE extends FF{
//
//}

class Normal{

    final void getName(){

    }
}

class ENormal extends Normal{
    //final修饰的成员方法不能被覆盖
//    void getName(){
//
//    }
}
