package com.mintlolly.basics.interfacedemo;

/**
 * Created on 2021/4/23
 * @author jiangbo
 * <p>
 * Description:
 */
public class Cat implements Felidae{
    @Override
    public void cry() {
        System.out.println("喵喵");
    }

    @Override
    public void feed() {
        System.out.println("喂猫");
    }
}
