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
            Thread.sleep(100);
            synchronized (o2){
                System.out.println("线程1成功拿到两把锁");
            }
        }
    }
    public void thread2() throws InterruptedException {
        synchronized(o2){
            System.out.println("线程2成功拿到1把锁");
            Thread.sleep(500);
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
        t2.start();


    }
}
