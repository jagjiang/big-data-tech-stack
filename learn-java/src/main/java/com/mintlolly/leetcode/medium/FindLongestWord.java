package com.mintlolly.leetcode.medium;

import java.util.*;

/**
 * Created on 2021/9/14
 *
 * @author jiangbo
 * Description:
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 * 示例 1：
 * <p>
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * 示例 2：
 * <p>
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindLongestWord {
    public static String findLongestWord(String s, List<String> dictionary) {
        //先对dictionary长度排序,如果长度相等，再按照字典序排序
        Collections.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String word1, String word2) {
                //优先长度
                if (word1.length() != word2.length()) {
                    //o1-o2 升序排序，o2-o1降序排序
                    return word2.length() - word1.length();
                } else {
                    //字典序排序
                    return word1.compareTo(word2);
                }
            }
        });
        //双指针法
        for (String word:dictionary) {
            //指向s和word
            int i = 0, j = 0;
            while(i < word.length() && j < s.length()){
                if(s.charAt(j) == word.charAt(i)){
                    i++;
                }
                j++;
            }
            if(i == word.length()){
                return word;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String[] dictionary =new String[]{"ale","apple","monkey","plea"};
        List<String> words = Arrays.asList(dictionary);
        System.out.println(findLongestWord("abpcplea", words));
    }
}
