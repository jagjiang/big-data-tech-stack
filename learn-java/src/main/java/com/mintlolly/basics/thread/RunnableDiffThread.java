package com.mintlolly.basics.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Create by on jiangbo 2020/6/17 14:19
 * <p>
 * Description:实际开发中一般使用Runnable接口的方式比较多，因为：
 * 1.从代码架构考虑 Runnable里只有一个run方法，run方法定义了需要执行的内容，实现Runnable与Thread的解耦，Thread 负责线程启动和属性设置等内容
 * 简单点说，如下代码，start需要new Thread
 * 2.使用继承Thread的方式，每次执行一次任务，都要新建一个独立的线程，实现Runnable接口的方式，就可以将线程传给线程池，使用一些固定线程完成任务
 * 不需要每次创建，销毁，大大降低性能开销
 * 3.不支持双继承，限制了代码代码可拓展性
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
 * 通过实现Runnable接口，重写run方法，之后需要把这个run方法的实例传到Thread类中就可以实现多线程
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

/**
 * 除了实现接口和继承类，线程池和Callable也是可以创建线程的，但是它们本质上也是通过前两种方式实现线程的创建
 *
 * 有返回值得Callable创建线程
 *
 * 无论是 Callable 还是 FutureTask，它们首先和 Runnable 一样，都是一个任务，
 * 是需要被执行的，而不是说它们本身就是线程。
 * 它们可以放到线程池中执行，如代码所示， submit() 方法把任务放到线程池中，
 * 并由线程池创建线程，不管用什么方法，
 * 最终都是靠线程来执行的，
 * 而子线程的创建方式仍脱离不了最开始讲的两种基本方式，也就是实现 Runnable 接口和继承 Thread 类。
 */

class CallableTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return new Random().nextInt();
    }

    //创建线程池
    ExecutorService service = Executors.newFixedThreadPool(10);
    //提交任务，并用 Future提交返回结果
    Future<Integer> future = service.submit(new CallableTask());
}

