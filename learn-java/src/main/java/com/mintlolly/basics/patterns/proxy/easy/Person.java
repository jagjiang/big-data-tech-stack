package com.mintlolly.basics.patterns.proxy.easy;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class Person implements IPerson{
    @Override
    public void eat() {
        System.out.println("人吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("人睡觉");
    }
}
