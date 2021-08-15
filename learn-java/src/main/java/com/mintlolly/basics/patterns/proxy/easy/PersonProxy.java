package com.mintlolly.basics.patterns.proxy.easy;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class PersonProxy implements IPerson{
    Person person = new Person();
    PersonProxy(Person person){
        this.person = person;
    }
    @Override
    public void eat() {
        person.eat();
    }

    @Override
    public void sleep() {
        person.sleep();
    }
}
