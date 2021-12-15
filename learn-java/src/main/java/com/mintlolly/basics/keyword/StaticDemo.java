package com.mintlolly.basics.keyword;

/**
 * Created on 2021/12/3
 *
 * @author jiangbo
 * Description:
 */
public class StaticDemo {

    static int a = 12;
    int b = 22;
    static String record="1初始值";
    String record2 ="2初始值";

    //非静态方法，可以访问类中的静态成员和非静态成员
    void getnum(){
        int f = a;
        int g = b;
    }
    public static void main(String[] args) {
        //静态方法可以直接调用同类中的静态成员变量，但不可调用非静态成员。
        int c = a;
//        int d = b;  //静态方法不可调用非静态成员。
        //使用非静态成员，需要通过创建对象的方式创建
        StaticDemo staticDemo = new StaticDemo();
        int e = staticDemo.b;
        //静态方法访问非静态方法，需要通过创建对象的方式进行调用。
        staticDemo.getnum();

    }
}
