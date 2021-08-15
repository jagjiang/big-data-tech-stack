package com.mintlolly.interview;

import java.lang.reflect.Field;

/**
 * Created on 2021/8/15
 *
 * @author jiangbo
 * Description:可以添加N行代码，但必须保证s引用的指向不变，最终将输出编程abcd
 */
public class Interview01 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s = new String("abc");
        Field value = s.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(s,"abcd".toCharArray());
        System.out.println(s);
    }
}
