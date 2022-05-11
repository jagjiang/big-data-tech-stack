package com.mintlolly.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;

/**
 * Created on 2022/1/6
 *
 * @author jiangbo
 * Description:简化Linux路径
 *https://leetcode-cn.com/problems/simplify-path/solution/jian-hua-lu-jing-by-leetcode-solution-aucq/
 *
 * /.././hadoop/../aaa  --> /aaa
 * /../hadoop/./aaa  --> /hadoop/aaa
 */
public class SimplifyPath {
    static Logger LOG = LoggerFactory.getLogger(SimplifyPath.class);
    public static void main(String[] args) {

    }
    public static String getSimplifyPath(String path){
        String[] split = path.split("/");
        String resultPath = "";
        Deque<String> deque = new ArrayDeque<>(Arrays.asList(split));
//        for (int i = 0; i < deque.size(); i++) {
//            // .. 回到上一目录 .当前目录
//            if(deque.getFirst().equals(".."){
//
//            }
//        }
        return null;
    }
}
