package com.mintlolly.interfacedemo;

/**
 * Create by on jiangbo 2021/1/7 17:17
 * <p>
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        LittleDog littleDog = new LittleDog();
        littleDog.feed();
        Animal.test();
    }
}

class LittleDog implements Dog{

    @Override
    public void feed() {
        System.out.println("喂小狗");
    }
}