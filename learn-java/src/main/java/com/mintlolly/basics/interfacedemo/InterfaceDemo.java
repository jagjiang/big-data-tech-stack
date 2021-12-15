package com.mintlolly.basics.interfacedemo;

/**
 * Created on 2021/12/6
 *
 * @author jiangbo
 * Description:
 */
public interface InterfaceDemo {
    int a = 20;
    public static void getName(){
        System.out.println("hello");
    }
    public default void getName2(){
        System.out.println("world");
    }

}
