package com.mintlolly.leetcode;

import java.util.HashMap;

/**
 * Created on 2021/4/21.
 * @author jiangbo.
 * Description:给定一个整数数组 nums 和一个目标值 target
 * 请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * https://leetcode-cn.com/problems/two-sum/ 两数之和
 */
public class TwoSum {
    //时间复杂度O(n^2)
    public static String twoSum(int[] nums,int target){
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if(target == nums[i] + nums[j]){
                    return i+","+j;
                }
            }
        }
        return "没有符合要求的结果";
    }

    public static String twoSum2(int[] nums,int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //前面已经添加所有的
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return i+","+map.get(complement);
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,18};
        System.out.println(twoSum(nums,7));
        System.out.println(twoSum2(nums,7));
    }
}
