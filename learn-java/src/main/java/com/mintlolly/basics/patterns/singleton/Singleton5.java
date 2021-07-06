package com.mintlolly.basics.patterns.singleton;

/**
 * Created on 2021/6/25
 *
 * @author jiangbo
 * Description:lazy loading
 * 懒汉式
 * 缺点：线程不安全 第一次解决加synchronized 带来效率下降，每次都要加锁
 */
public class Singleton5 {
    public static Singleton5 INSTANCE;
    private Singleton5(){};
    public static  Singleton5 getInstance(){
        if(null == INSTANCE){
            //多个线程同时进入了if,后面即使加了锁也是有问题的，还是会进行初始化
            //妄图通过减少同步代码块的方式提高效率，然后不可行
            synchronized (Singleton5.class){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton5();
            }

        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Singleton5.getInstance().hashCode());
            }).start();
        }

    }
}
