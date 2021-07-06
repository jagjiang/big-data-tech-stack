package com.mintlolly.leetcode;

/**
 * @author jiangbo
 * Created on 2021/4/20
 * Description:爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * https://leetcode-cn.com/problems/climbing-stairs/
 */
public class ClimbingStairs {
    /**
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233
     * 斐波那契数列 计算第N个数的数值
     * 递归实现（反例）该算法的时间复杂度已经属于O(2^n)了。指数级别时间复杂度的算法跟不能用没啥区别！
     *
     * @param n
     * @return
     */
    private static Integer fib(int n) {
        if (1 == n) {
            return 0;
        } else if (2 == n) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    /**
     * 动态规划解决斐波那契数列
     * 空间换时间 时间复杂度为O(n)
     * @param n
     * @return
     */
    private static Integer fib2(int n) {
        int[] results = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                results[i] = i;
            } else {
                results[i] = results[i - 1] + results[i - 2];
            }
        }
        return results[results.length-1];
    }

    /**
     * @param n 台阶个数
     * @return
     */
    private static Integer climbingStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        // n = 3;i=3;i<=3;dp[3]=dp[2]+dp[1] dp[3] = 1+2 = 3种;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        //斐波那契递归实现
        long start = System.currentTimeMillis();
        System.out.println(fib(40));
        System.out.println(System.currentTimeMillis()-start);
        //斐波那契动态规划
        start = System.currentTimeMillis();
        System.out.println(fib2(40));
        System.out.println(System.currentTimeMillis()-start);
        //爬楼梯
        System.out.println(climbingStairs(4));
    }

}
