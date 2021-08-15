package com.mintlolly.basics.patterns.proxy.easy;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class Client {
    public static void main(String[] args) {
        Person person = new Person();
        person.eat();
        person.sleep();
        //代理模式
        PersonProxy personProxy = new PersonProxy(person);
        personProxy.eat();
        personProxy.sleep();
    }
}
