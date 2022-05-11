package com.mintlolly.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created on 2021/12/27
 *
 * @author jiangbo
 * Description:
 */
public class NumFriendRequests {
    public static void main(String[] args) {
        int[] ages = {120,110,100,30,20};
        System.out.println(getNumFriendRequests(ages));
    }
    public static int getNumFriendRequests(int[] ages){
        int len = ages.length;
        int requests = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1;j < len-1;j++){
                if(isSendRequest(ages[i],ages[j])){
                    System.out.println(ages[i]+"->"+ages[j]);
                    requests+=1;
                }
            }
        }
        return requests;
    }
    public static boolean isSendRequest(int x,int y){
        return !(y <= 0.5 * x + 7) || y > x || (y > 100 || x < 100);
    }
}
