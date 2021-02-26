package com.mintlolly.jvm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by on jiangbo 2021/2/25 13:16
 * <p>
 * Description: -Xms200M -Xmx200M -XX:+PrintGC
 */
public class FullGC_Problem {
    private static class CardInfo{
        BigDecimal price = new BigDecimal(0.0);
        String name = "zhangsan";
        int age = 5;
        Date birthdate = new Date();
        public void m(){}
    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws InterruptedException{
        executor.setMaximumPoolSize(50);

        for(;;){
            modelFilt();
            Thread.sleep(100);
        }
    }

    public static void modelFilt(){
        List<CardInfo> taskList = getAllCardInfo();

//        taskList.forEach(info ->executor.schedule(() -> info.m(),3, TimeUnit.SECONDS));
        taskList.forEach(info -> executor.scheduleWithFixedDelay(() -> info.m(),2,3, TimeUnit.SECONDS));

    }

    private static List<CardInfo> getAllCardInfo(){
        List<CardInfo> taskList= new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }
        return taskList;
    }
}
