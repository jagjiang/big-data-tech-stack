package com.mintlolly.algorithm;

/**
 * Created on 2021/11/23
 *
 * @author jiangbo
 * Description:在 2.5亿个整数中，找出不重复的整数，注意：内存不足以容纳 2.5亿个整数。
 * 电话号码也可以以类似的形式处理
 * 思想：bit位则代表是哪个数字 例如 32位就是整数32
 *
 */
public class LargeAmountBitMap {
    static int[] bitArray;
    //根据整数个数确定需要多大的bit位，以整数数组为数据结构
    public static int[] BitMap(long num){
        bitArray = new int[(int)num/32+1];
        return bitArray;
    }
    //计算num在bitArray的位置
    public static int getIndex(int num){
        return num >> 5;
    }
    //计算num在bitArray[xxx]的bit位
    public static int getPosition(int num){
        return num & 0x1F;
    }
    //将所在位置从0变成1，其它位置不变
    public static void add(int[] bits,int num){
        bits[getIndex(num)] |= 1 << getPosition(num);
    }
    /**
     * 判断指定数字num是否存在<br/>
     * 将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
     * @param bits
     * @param num
     * @return
     */
    public static boolean contains(int[] bits, int num){
        return (bits[getIndex(num)] & 1 << getPosition(num)) != 0;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,1280000};
        int[] ints = BitMap(1280000);
        for (int i = 0; i < nums.length; i++) {
            add(ints,nums[i]);
        };
        System.out.println(contains(ints,1280000));
    }
}
