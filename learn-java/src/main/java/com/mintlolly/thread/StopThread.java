package com.mintlolly.thread;

import java.util.concurrent.BlockingQueue;

/**
 * Create by on jiangbo 2020/9/17 15:03
 * <p>
 * Description: 对于java而言，最正确的停止方式是使用interrupt，只是通知被停止的线程
 * 对于被停止的线程，拥有完全的自主权，避免立即停止造成的数据问题。
 */
public class StopThread implements Runnable {
    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println("count = " + count++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(5);
        thread.interrupt();

        Runnable runnable = () -> {
            int num = 0;
            while (!Thread.currentThread().isInterrupted() && num <= 1000) {
                System.out.println(num);
                num++;
                /**
                 * 如果 sleep、wait 等可以让线程进入阻塞的方法使线程休眠了，
                 * 而处于休眠中的线程被中断，那么线程是可以感受到中断信号的，
                 * 并且会抛出一个 InterruptedException 异常，同时清除中断信号，将中断标记位设置成 false。
                 */
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
                }
            }
        };

        thread = new Thread(runnable);
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(5);
        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println(thread.getState());
    }
}
