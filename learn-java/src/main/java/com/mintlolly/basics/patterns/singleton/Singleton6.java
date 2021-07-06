package com.mintlolly.basics.patterns.singleton;

/**
 * Created on 2021/6/25
 *
 * @author jiangbo
 * Description:lazy loading
 * dcl
 */
public class Singleton6 {
    public static volatile Singleton6 INSTANCE;
    private Singleton6(){};
    public static Singleton6 getInstance(){
        if(null == INSTANCE){
            //多个线程同时进入了if,后面即使加了锁也是有问题的，还是会进行初始化
            //妄图通过减少同步代码块的方式提高效率，然后不可行
            synchronized (Singleton6.class){
                if(null == INSTANCE){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Singleton6();
                }
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
                System.out.println(Singleton6.getInstance().hashCode());
            }).start();
        }

    }
}
