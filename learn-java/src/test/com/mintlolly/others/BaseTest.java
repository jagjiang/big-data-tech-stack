package com.mintlolly.others;

/**
 * Created on 2021/6/29
 *
 * @author jiangbo
 * Description:java基础测试
 */
public class BaseTest {
    public static void main(String[] args) {
        System.out.println((char)( 1+'a'));
        StringBuilder sb = new StringBuilder();
        sb.append('a');
        sb.append('b');
        System.out.println(sb.reverse().toString());
    }
}
