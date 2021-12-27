package com.mintlolly.scala.demo;

/**
 * Created on 2021/12/15
 *
 * @author jiangbo
 * Description:
 */
public class PersonJava {
    private String name;
    private int age;
    public String sex;

    PersonJava(){

    }
    PersonJava(String name,int age){
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonJava{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
