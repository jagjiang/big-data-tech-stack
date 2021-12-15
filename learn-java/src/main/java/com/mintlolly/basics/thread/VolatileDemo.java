package com.mintlolly.basics.thread;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Create by on jiangbo 2021/2/2 13:27
 * <p>
 * Description:
 */
public class VolatileDemo {
    private volatile Integer num = 2;
    public void get(){}
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<VolatileDemo> volatileDemoClass = VolatileDemo.class;
        for (Method method : volatileDemoClass.getMethods()) {
            System.out.println(method.getName());
        }
        String a = "123";
        Field value = a.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(a,"1234".toCharArray());
        System.out.println();
    }
}
