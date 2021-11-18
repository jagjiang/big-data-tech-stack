package com.mintlolly.basics.thread;

/**
 * Create by on jiangbo 2020/9/18 16:08
 * <p>
 * Description:死锁
 */
public class DeadLock {
    Object o1 = new Object();
    Object o2 = new Object();

    public void thread1() throws InterruptedException {
        synchronized(o1){
            System.out.println("线程1成功拿到1把锁");
            //wait 释放锁 Object方法
//            o1.wait(1);
            //sleep未释放锁Thread方法
//            Thread.sleep(100);
            synchronized (o2){
                System.out.println("线程1成功拿到两把锁");
            }
        }
    }
    public void thread2() throws InterruptedException {
        synchronized(o2){
            System.out.println("线程2成功拿到1把锁");
//            Thread.sleep(500);
            synchronized (o1){
                System.out.println("线程2成功拿到两把锁");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock deadLock = new DeadLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLock.thread1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLock.thread2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        //当前线程要锁定该对象之后，才能用锁定的对象执行这些方法，
        // 这里需要用到synchronized关键字，
        // 锁定哪个对象就用哪个对象来执行notify(), notifyAll(),wait(), wait(long), wait(long, int)操作，
        // 否则就会报IllegalMonitorStateException异常。
//        t1.wait();
        t2.start();


    }
}
