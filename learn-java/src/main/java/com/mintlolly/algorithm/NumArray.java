package com.mintlolly.algorithm;

/**
 * Create by on jiangbo 2021/3/1 10:57
 * <p>
 * Description:前缀和
 */
public class NumArray {
    int[] sums;
    public NumArray(int[] nums){
        int n = nums.length;
        sums = new int[n+1];
        for (int i = 0; i < n; i++) {
            sums[i+1] = sums[i]+nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }

    public static void main(String[] args) {
        NumArray num = new NumArray(new int[]{1,2,3,4,5,6});
        int sum = num.sumRange(1, 2);
        System.out.println(sum);
    }
}
