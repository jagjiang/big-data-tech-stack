package com.mintlolly.basics.cpu;

import java.util.concurrent.CountDownLatch;

/**
 * Create by on jiangbo 2021/2/22 9:03
 * <p>
 * Description: 64位对齐， x一定是volatile的才能看到效果，如果没有注释部分，
 * 内存可见性，cpu内存会频繁通知arr中有一个x被改变了
 * new T 如果只有一个long 那么 arr中两个元素在同一块中，一个改变也会通知其他cpu,我改变了。
 * 如果加上注释部分，arr两个元素在两个块中，就不用互相通知了。
 */
public class CPU_Cache {

    private static long count = 100000000L;

    private  static class T{
//        long p1,p2,p3,p4,p5,p6,p7;
        volatile long x = 0L;
    }

    public static T[] arr = new T[2];
    static {
        arr[0] = new T();
        arr[1] = new T();
    }


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1 = new Thread(()->{
            for (long i = 0; i < count; i++) {
                arr[0].x = count;
            }
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < count; i++) {
                arr[1].x = count;
            }
            countDownLatch.countDown();
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        countDownLatch.await();
        long end = System.currentTimeMillis();

        System.out.println(end-start);
    }
}
