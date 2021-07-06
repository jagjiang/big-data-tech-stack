package com.mintlolly.algorithm;

/**
 * Create by on jiangbo 2021/3/3 9:39
 * <p>
 * Description:位运算
 */
public class BitCounts {

    public int[] countBits(int num){
        int[] bits = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    private int countOnes(int x) {
        int ones = 0;
        while(x > 0){
            x &= x - 1;
            ones ++;
        }
        return ones;
    }

    public static void main(String[] args) {
        BitCounts bitCounts = new BitCounts();
        int[] ints = bitCounts.countBits(4);

        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }
}
