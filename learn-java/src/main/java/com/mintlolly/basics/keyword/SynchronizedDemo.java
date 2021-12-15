package com.mintlolly.basics.keyword;

/**
 * Created on 2021/12/4
 *
 * @author jiangbo
 * Description:
 */
public class SynchronizedDemo implements Runnable {
    static int count = 0;
//*****************************************************************************
//    @Override
//    public void run() {
//    //锁SynchronizedDemo 锁定的类对象可以保证多个实例之间调用的线程安全
//        synchronized (SynchronizedDemo.class) {
//            for (int i = 0; i < 10000; i++) {
//                count += 1;
//            }
//        }
//    }
//*****************************************************************************
//    //锁定静态方法 锁定的是类对象
//    public synchronized static void method() {
//        for (int i = 0; i < 10000; i++) {
//            count++;
//        }
//    }
//
//    @Override
//    public void run() {
//        method();
//    }
//*****************************************************************************
//    //String对象作为锁
//    String lock = "";
//    @Override
//    public void run() {
//        synchronized (lock){
//            for (int i = 0; i < 10000; i++) {
//                count++;
//            }
//        }
//    }
//*****************************************************************************
    //锁定的类的对象实例
//    public synchronized void run() {
//        for (int i = 0; i < 10000; i++) {
//            count ++;
//        }
//    }
//*****************************************************************************
    //锁定的类的对象实例
    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }
    }
//*****************************************************************************

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SynchronizedDemo());
            thread.start();
        }
        try {
            Thread.sleep(10_00);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }


}
