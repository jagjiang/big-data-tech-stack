package com.mintlolly.basics.patterns.singleton;

/**
 * Created on 2021/6/25
 *
 * @author jiangbo
 * Description:饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用
 * 唯一缺点：不管用到与否，类装载时就完成实例化
 */
public class Singleton1 {
    private static final Singleton1 INSTANCE = new Singleton1();
    //private 外面不允许实例化
    private Singleton1(){}
    public static Singleton1 getInstance(){
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }
    public static void main(String[] args) {
        Singleton1 s1 = Singleton1.getInstance();
        s1.m();
    }
}
