package com.mintlolly.others;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Create by on jiangbo 2020/5/21 10:28
 * <p>
 * Description:Duration 用于计算两个时间间隔
 * Duration 被声明final（immutable），并且线程安全。
 */
public class DurationDemo {
    public static void main(String[] args) {
        //Zero常量
        Duration duration;
        System.out.println("--------------------------zero start--------------------------");
        duration = Duration.ZERO;
        System.out.println("持续时间常量值:"+duration.toNanos());
        System.out.println("持续时间常量值是否为零:"+duration.isZero());
        System.out.println("持续时间常量值是正数:"+duration.abs().toNanos());
        System.out.println("--------------------------zero end--------------------------");

        //ofDays ofSeconds ofMillis.....等等很多

        System.out.println("--------------------------ofDays start--------------------------");
        duration = Duration.ofDays(2);
        System.out.println("两天有多少小时："+duration.toHours()+"H");
        System.out.println("两天有多少天："+duration.toDays()+"D");
        System.out.println("获取纳秒数："+duration.getNano());
        System.out.println("--------------------------ofDays end--------------------------");


        System.out.println("--------------------------ofNanos start--------------------------");
        duration = Duration.ofNanos(100000000);
        System.out.println("10000纳秒有多少纳秒:"+duration.toNanos()+"ns");
        System.out.println("100000000纳秒有多少毫秒:"+duration.toMillis()+"ms");

        duration = Duration.ofSeconds(1000,2);
        System.out.println("ofSecond 可以传一个毫秒的参数："+ duration.toNanos());
        System.out.println("--------------------------ofNanos end--------------------------");

        Duration durationBetween =  Duration.between(LocalTime.NOON,LocalTime.MAX);
        System.out.println("距离今天结束还有多少时间:"+durationBetween.toHours()+"时"+durationBetween.toMinutes()+"分"+durationBetween.getSeconds()+"秒");

    }
}
