package com.mintlolly.leetcode.easy;

/**
 * Created on 2021/10/18
 *
 * @author jiangbo
 * Description:给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。
 */
public class BitOperation {
    //bit位从零开始，所以4 100 1的最高位为2
    public static int findComplement(int num) {
        //获取最高位
        int highbit = 0;
        for (int i = 1; i <= 30; ++i) {
            if (num >= 1 << i) {
                highbit = i;
            } else {
                break;
            }
        }

        int mask = highbit == 30 ? 0x7fffffff : (1 << (highbit + 1)) - 1;
        //按位取反与全1异或
        return num ^ mask;
    }

    public static void main(String[] args) {
        System.out.println(findComplement(753537225));
    }
}
