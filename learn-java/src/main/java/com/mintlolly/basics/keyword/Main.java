package com.mintlolly.basics.keyword;

/**
 * Created on 2021/12/3
 *
 * @author jiangbo
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        //静态变量线程能感知到线程2对静态变量的修改
        new Thread(() -> {
            System.out.println("线程1");
            StaticDemo staticDemo = new StaticDemo();
            StaticDemo.record = "线程1第一次被修改record";
            staticDemo.record2 = "线程1第一次被修改record2";
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(StaticDemo.record);
            System.out.println(staticDemo.record2);

        }).start();
        new Thread(() -> {
            System.out.println("线程2");
            StaticDemo staticDemo = new StaticDemo();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StaticDemo.record = "线程2第二次被修改record";
            staticDemo.record2 = "线程2第二次被修改record2";
        }).start();

       new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }).start();

    }
}
