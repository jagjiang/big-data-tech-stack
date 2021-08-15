package com.mintlolly.leetcode.medium;

/**
 * Create by on jiangbo 2021/3/3 10:01
 * <p>
 * Description: java位运算操作
 * java中的位运算是针对整型的数据类型进行运算的，所以操作数必须是以下几种
 * byte  8bit
 * short 16bit
 * int   32bit
 * long  64bit
 * char  16bit
 *
 * 位运算
 *   & 按位与
 *   | 按位或
 *   ~ 按位非
 *   ^ 按位异或
 *   << 左移
 *   >> 右移
 *   <<< 无符号右移动
 */
public class BitOperation {
    public static void main(String[] args) {
        int i = 6;
        int bitCount = Integer.bitCount(i);
        System.out.println(bitCount);
    }
}
