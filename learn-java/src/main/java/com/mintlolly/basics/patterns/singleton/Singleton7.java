package com.mintlolly.basics.patterns.singleton;

/**
 * Created on 2021/6/25
 *
 * @author jiangbo
 * Description:lazy loading
 * 静态内部类方法
 * JVM保证单例
 * 加载外部类时不会加载内部类，这样可以实现懒汉式
 */
public class Singleton7 {
    private Singleton7(){};
    //静态内部类
    private static class Singleton7Holder{
        public final static Singleton7 INSTANCE = new Singleton7();
    }
    public Singleton7 getInstance(){
        return Singleton7Holder.INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(new Singleton7().getInstance().hashCode());
            }).start();
        }

    }
}
