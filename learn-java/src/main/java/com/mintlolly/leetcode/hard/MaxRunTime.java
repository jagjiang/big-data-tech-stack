package com.mintlolly.leetcode.hard;

import java.util.Arrays;

/**
 * Created on 2022/5/14
 *
 * @author jiangbo
 * Description:
 * https://leetcode.cn/problems/maximum-running-time-of-n-computers/
 */
public class MaxRunTime {
    public static void main(String[] args) {
        int[] batter= new int[]{10,10,3,5};
        System.out.println(MaxRunTime(3,batter));
        System.out.println(maxRunTime2(3,batter));
    }

    public static long maxRunTime2(int n, int[] batteries) {
        long left = 0;
        long right = 0;
        for(int battery:batteries){
            right += battery;
        }
        right = right/n;
        while(left <right){
            long mid = (right-left+1)/2+left;
            long sum = 0L;
            for(int battery:batteries){
                sum += Math.min((long)battery,mid);
            }

            if(sum >= n*mid){
                left = mid;
            }else{
                right = mid-1;
            }
        }
        return left;
    }

    /**
     *
     * @param n  机器数量
     * @param batteries  电池情况
     * @return
     */
    public static int MaxRunTime(int n,int[] batteries){
        Arrays.sort(batteries);
        //求总电池的累加和sum，那么能撑的值的返回肯定在0-sum
        int batteriesNum = batteries.length;
        //先说结论：累加和 >=供电机器数量*最大支持时间则一定能支撑
        //sum存放从0开始电量的累加和
        long[] sum = new long[batteriesNum];
        sum[0] = batteries[0];
        for (int i = 1; i < batteriesNum; i++) {
            sum[i] = sum[i-1]+batteries[i];
        }
        long left = 0;
        long mid;
        long right = sum[batteriesNum-1];  //28
        long sumBatteries = sum[batteriesNum-1];  //28 n=3
        while (left <= right){
            System.out.println("right="+ right+","+"left="+left);
            mid = (left + right) /2;
            long sums = 0;
            for(int i = 0;i<batteriesNum;i++){
                sums+=Math.min(batteries[i],mid);
            }
            if(sums < n*mid){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return (int)right;
    }
}
