package com.mintlolly.basics.patterns.singleton;

/**
 * Created on 2021/6/25
 *
 * @author jiangbo
 * Description:和1是一个意思
 */
public class Singleton2 {
    private static final Singleton2 INSTANCE;
    static {
        INSTANCE = new Singleton2();
    }
    private Singleton2(){}
    public static Singleton2 getInstance(){
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Singleton2 s2_1 = Singleton2.getInstance();
        Singleton2 s2_2 = Singleton2.getInstance();
        System.out.println(s2_1 == s2_2);
    }
}
