package com.mintlolly.basics.patterns.singleton;

/**
 * Created on 2021/6/25
 *
 * @author jiangbo
 * Description:lazy loading
 * 懒汉式
 * 缺点：线程不安全
 */
public class Singleton3 {
    public static Singleton3 INSTANCE;
    private Singleton3(){};
    public static Singleton3 getInstance(){
        if(null == INSTANCE){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //放在这里不会出现线程不安全，因为不是多线程访问的，放到线程里面才对。
//            Singleton3 s3 = Singleton3.getInstance();
            new Thread(()->{
                System.out.println(Singleton3.getInstance().hashCode());
            }).start();
        }

    }
}
