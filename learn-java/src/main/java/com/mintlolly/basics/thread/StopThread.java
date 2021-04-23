package com.mintlolly.basics.thread;

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
            System.out.println("count =  " + count++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread stopThread = new Thread(new StopThread());
        stopThread.start();
        Thread.sleep(5);
        stopThread.interrupt();
        //this.interrupted(): 测试当前线程是否已经中断； this.isInterrupted(): 测试线程是否已经中断；
        //this.interrupted()方法的解释：测试当前线程是否已经中断，当前线程是指运行this.interrupted()方法的线程。
        System.out.println("stop 1->" + stopThread.interrupted());
        System.out.println("stop 2->" + stopThread.interrupted());

        System.out.println("stop 1.1->" + stopThread.isInterrupted());
        System.out.println("stop 2.1->" + stopThread.isInterrupted());

        /**
         * 从控制台打印的结果来看，线程并未停止，这也证明了interrupted()方法的解释，
         * 测试当前线程是否已经中断。这个当前线程是main，它从未中断过，所以打印的结果是两个false.
         */

        Thread.currentThread().interrupt();
        System.out.println("stop 3->" + Thread.interrupted());
        System.out.println("stop 4->" + Thread.interrupted());

        System.out.println("End");

        /**
         * 方法interrupted()的确判断出当前线程是否是停止状态。但为什么第2个布尔值是false呢？
         * 官方帮助文档中对interrupted方法的解释： 测试当前线程是否已经中断。
         * 线程的中断状态由该方法清除。 换句话说，如果连续两次调用该方法，则第二次调用返回false。
         */

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

        Thread runnableThread = new Thread(runnable);

//        System.out.println(thread.getState());
//        thread.start();
//        System.out.println(thread.getState());
//        Thread.sleep(5);
//        System.out.println(thread.getState());
//        thread.interrupt();
//        System.out.println(thread.getState());
    }
}
