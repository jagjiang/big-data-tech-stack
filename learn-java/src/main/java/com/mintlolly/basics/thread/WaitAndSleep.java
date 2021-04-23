package com.mintlolly.basics.thread;

import java.util.stream.Stream;

/**
 * Create by on jiangbo 2021/1/28 14:29
 * <p>
 * Description:wait 方法和sleep的区别
 */
public class WaitAndSleep {
    private static Object LOCK = new Object();

    public static void main(String[] args) throws Exception {
        sleepWaitExamples();

        ThreadB b = new ThreadB();
        Thread threadB = new Thread(b);
        threadB.start();

        synchronized (b) {
            while (b.sum == 0) {
                System.out.println("Waiting for ThreadB to complete...");
                //需要使用notify唤醒 如果 ThreadB 中没有唤醒方法，则会一直阻塞
                b.wait();
            }

            System.out.println("ThreadB has completed. " +
                    "Sum from that thread is: " + b.sum);
        }

        //sleep不释放锁  wait会释放锁
        System.out.println("================================sleep不释放锁  wait会释放锁=======================");
        Stream.of("sleep线程1", "sleep线程2").forEach(n -> new Thread(n) {
            public void run() {
                sleepLock();
            }
        }.start());
        Stream.of("wait线程1", "wait线程2").forEach(n -> new Thread(n) {
            public void run() {
                waitLock();
            }
        }.start());

        Thread.sleep(10_000);
        //sleep不依赖同步方法 wait依赖
        System.out.println("========================================去掉synchronized========================================");
        Stream.of("sleep线程1", "sleep线程2").forEach(n -> new Thread(n) {
            public void run() {
                sleepSync();
            }
        }.start());
        Stream.of("wait线程1", "wait线程2").forEach(n -> new Thread(n) {
            public void run() {
                waitSync();
            }
        }.start());


    }

    private static void sleepWaitExamples() throws InterruptedException {

        Thread.sleep(1000);
        System.out.println(
                "Thread '" + Thread.currentThread().getName() +
                        "' is woken after sleeping for 1 second");

        synchronized (LOCK) {
            LOCK.wait(1000);
            System.out.println("Object '" + LOCK + "' is woken after" +
                    " waiting for 1 second");
        }
    }

    private static void sleepLock() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName() + "正在执行");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "休眠结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void waitLock() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName() + "正在执行");
                LOCK.wait(1000);
                System.out.println(Thread.currentThread().getName() + "休眠结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void sleepSync() {
        try {
            System.out.println(Thread.currentThread().getName() + "正在执行");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "休眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void waitSync() {
        try {
            System.out.println(Thread.currentThread().getName() + "正在执行");
            LOCK.wait(1000);
            System.out.println(Thread.currentThread().getName() + "休眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class ThreadB implements Runnable {
    int sum;
    @Override
    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 0;
            while (i < 100000) {
                sum += i;
                i++;
            }
            notify();
        }
    }
}


