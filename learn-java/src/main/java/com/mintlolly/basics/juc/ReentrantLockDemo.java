package com.mintlolly.basics.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2021/9/16
 *
 * @author jiangbo
 * Description:
 */
public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cA = lock.newCondition();
    private static Condition cB = lock.newCondition();
    private static Condition cC = lock.newCondition();
    private static CountDownLatch latchB = new CountDownLatch(1);
    private static CountDownLatch latchC = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i+" A");
                    cB.signal();
                    if (i == 0) latchB.countDown();
                    cA.await();
                }
                cB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i+" B");
                    cC.signal();
                    if (i == 0) latchC.countDown();
                    cB.await();
                }
                cC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread threadC = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i+" C");
                    cA.signal();
                    if (i == 0) latchB.countDown();
                    cC.await();
                }
                cA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}