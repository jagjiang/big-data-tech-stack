package com.mintlolly.others;

import java.util.Stack;

/**
 * Create by on jiangbo 2020/5/29 17:01
 * <p>
 * Description:栈是Vector的一个子类，它实现了一个标准的后进先出的栈
 */
public class StackDemo {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("aaa");
        stack.push("bbb");
        stack.push("ccc");

        System.out.println(stack.pop());

        for (int i = 0; i <= stack.size(); i++) {
            System.out.println(stack.pop());
        }
    }
}
