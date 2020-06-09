package com.mintlolly.juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Create by on jiangbo 2020/6/9 9:19
 * <p>
 * Description: arraylist 对应的高并发类
 * 它相当于线程安全的ArrayList。和ArrayList一样，它是个可变数组
 * 但是和ArrayList不同的是，它具有以下特性：
 * 1. 它最适合于具有以下特征的应用程序：List 大小通常保持很小，只读操作远多于可变操作，需要在遍历期间防止线程间的冲突。
 * 2. 它是线程安全的。
 * 3. 因为通常需要复制整个基础数组，所以可变操作（add()、set() 和 remove() 等等）的开销很大。
 * 4. 迭代器支持hasNext(), next()等不可变操作，但不支持可变 remove()等操作。
 * 5. 使用迭代器进行遍历的速度很快，并且不会与其他线程发生冲突。在构造迭代器时，迭代器依赖于不变的数组快照。
 */
public class CopyOnWriteArrayListDemo {

//    private static List<String> list = new ArrayList<>();
    private static List<String> list = new CopyOnWriteArrayList<>();

    private static void printAll(){
        for (String s : list) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread{

        MyThread(String name){
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 6){
                list.add(Thread.currentThread().getName()+"--"+i);
                printAll();
            }
        }
    }

    public static void main(String[] args) {
        new MyThread("ta").start();
        new MyThread("tb").start();

    }
}
