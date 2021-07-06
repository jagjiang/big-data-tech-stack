package com.mintlolly.basics.patterns.singleton;

/**
 * Created on 2021/6/25
 *
 * @author jiangbo
 * Description:枚举单例 不仅可以解决线程同步，还可以防止反序列化
 */
public enum Singleton8 {
    INSTANCE;
    public void m(){}

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                Singleton8.INSTANCE.m();
            }).start();
        }
    }
}
