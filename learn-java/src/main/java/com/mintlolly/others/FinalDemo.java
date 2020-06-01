package com.mintlolly.others;

import java.util.ArrayList;

/**
 * Create by on jiangbo 2020/5/28 15:55
 * <p>
 * Description:
 * final非常不幸的只能用来保证原始数据类型的实例变量的不可变性
 * 而无法用于引用类型的变量
 *
 * 如果一个应用类型的实例变量含有final,该实例变量的值（某个对象的引用就永远无法改变）
 * 他将永远指向同一个对象，但对象的值本身仍然是可变的。
 */
public class FinalDemo{

    //  并未实现不可变数据类型

    private final int[] a;

    public FinalDemo(int[] a) {
        this.a = a;
    }

    public static void main(String[] args) {
        int[] a = {124,234};
        FinalDemo finalDemo = new FinalDemo(a);

        //final 里的a本身不能修改，但是却通过引用变量，绕过构造方法的api
        // 外部的a 不可变，里面的值确实可以改变的
        // finalDemo.a = a;
        finalDemo.a[0] = 111;


        for (int i : finalDemo.a) {
            System.out.println(i);
        }

    }

}
