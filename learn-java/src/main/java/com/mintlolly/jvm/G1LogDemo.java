package com.mintlolly.jvm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Create by on jiangbo 2020/7/29 14:40
 * <p>
 * Description:
 * 启动参数
 * -Xms100m -Xmx100m -XX:+UseG1GC -XX:+PrintGC -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps  -XX:+PrintHeapAtGC -Xloggc:./g1-gc.log
 */
public class G1LogDemo {
    private static final int MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {

        // eden
        System.out.println(getCurrentTime() + " before allocation1 ");
        byte[] allocation = new byte[42 * MB];
        System.out.println(getCurrentTime() + " after allocation1 \n");
        Thread.sleep(2000);

        // 触发minor gc  == young gc
        System.out.println(getCurrentTime() + " before allocation2 ");
        allocation = new byte[3 * MB];
        System.out.println(getCurrentTime() + " after allocation2 \n");
        Thread.sleep(2000);

        byte[][] allByte = new byte[10][];

        // old gc
        for (int i = 0; i < 10; ++i) {
            System.out.println(getCurrentTime() + " before list allocation, index = " + i);
            allByte[i] = new byte[42 * MB];

            System.out.println(getCurrentTime() + " after list allocation, index = " + i + "\n");
            Thread.sleep(2000);
        }
        Thread.sleep(3000);
    }

    private static String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
