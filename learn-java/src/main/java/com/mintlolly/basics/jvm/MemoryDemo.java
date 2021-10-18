package com.mintlolly.basics.jvm;

/**
 * Created on 2021/10/13
 *
 * @author jiangbo
 * Description:
 */
public class MemoryDemo {
    private static final int GB = 1024 * 1024 *1024;
    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("total:" + runtime.totalMemory()/1024/1024+"mb");
        System.out.println("free:" + runtime.freeMemory()/1024/1024+"mb");
        System.out.println("max:" + runtime.maxMemory()/1024/1024+"mb");
        System.out.println("======================================");
        byte[] bytes = new byte[1 * GB];
        System.out.println("total:" + runtime.totalMemory()/1024/1024+"mb");
        System.out.println("free:" + runtime.freeMemory()/1024/1024+"mb");
        System.out.println("max:" + runtime.maxMemory()/1024/1024+"mb");
        System.out.println("======================================");
        //int 8个字节 int[GB] = 8GB / 2 = 4 GB 申请了4GB的内存
        int[] ints = new int[GB/2];
        System.out.println("total:" + runtime.totalMemory()/1024/1024+"mb");
        System.out.println("free:" + runtime.freeMemory()/1024/1024+"mb");
        System.out.println("max:" + runtime.maxMemory()/1024/1024+"mb");
        System.out.println(Integer.MAX_VALUE);
        Thread.sleep(10_000);
    }
}
