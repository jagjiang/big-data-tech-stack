package com.mintlolly.thread;

/**
 * Create by on jiangbo 2020/6/17 14:19
 * <p>
 * Description:实际开发中一般使用Runnable接口的方式比较多，因为：
 * 通过继承Thread类的方式，可以完成多线程的建立。但是这种方式有一个局限性，如果一个类已经有了自己的父类，就不可以继承Thread类
 * 而实现Runnable接口可以避免单继承的局限性。
 * 其他的好像没什么区别，继承thread的类的可以直接创建执行start
 * 实现接口的只能调用方法run
 */
public class RunnableDiffThread {
    public static void main(String[] args) throws InterruptedException {
        MyThreadExtendsThread mtt1 = new MyThreadExtendsThread("一号窗口");
        MyThreadExtendsThread mtt2 = new MyThreadExtendsThread("二号窗口");
        MyThreadExtendsThread mtt3 = new MyThreadExtendsThread("三号窗口");
        mtt1.start();
        mtt2.start();
        mtt3.start();
        Thread.sleep(5000L);

        System.out.println("---------------------------------------------------");
        MyThreadExtendsThread mtet = new MyThreadExtendsThread();
        Thread mt1 = new Thread(mtet,"一号");
        Thread mt2 = new Thread(mtet,"二号");
        Thread mt3 = new Thread(mtet,"三号");
        mt1.start();
        mt2.start();
        mt3.start();
        Thread.sleep(5000L);

        System.out.println("---------------------------------------------------");
        MyThreadImplRunnable myThreadImplRunnable = new MyThreadImplRunnable();
        Thread t1 = new Thread(myThreadImplRunnable,"一号窗口");
        Thread t2 = new Thread(myThreadImplRunnable,"二号窗口");
        Thread t3 = new Thread(myThreadImplRunnable,"三号窗口");
        t1.start();
        t2.start();
        t3.start();

    }
}

/**
 * 通过实现Runnable接口，实例化Thread类
 */
class MyThreadImplRunnable implements Runnable{
    private int ticket = 10;
    private String name;

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            if(this.ticket > 0){
                System.out.println(Thread.currentThread().getName()+"卖票------->" + (this.ticket--));
            }
        }
    }
}

/**
 * 通过继承Thread类，重写Thread的run方法，将线程运行的逻辑放在其中
 */
class MyThreadExtendsThread extends Thread{
    private int ticket = 10;
    private String name;

    MyThreadExtendsThread(){}


    MyThreadExtendsThread(String name){
        this.name = name;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            if(this.ticket > 0){
//                System.out.println(this.name+"卖票------->" + (this.ticket--));
                System.out.println(Thread.currentThread().getName()+"卖票------->" + (this.ticket--));
            }
        }
    }
}