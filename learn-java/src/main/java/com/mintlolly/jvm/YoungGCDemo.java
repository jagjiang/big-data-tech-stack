package com.mintlolly.jvm;

/**
 * Create by on jiangbo 2020/7/28 16:47
 * <p>
 * Description:
 * 1Byte(字节) = 8bit(位)
 * 1KB = 1024Byte(字节)
 * 1MB = 1024KB
 * 1GB = 1024MB
 * 1TB = 1024GB
 */
public class YoungGCDemo {
    public static void main(String[] args) {
        /**
         * 创建三个16M大小的数组
         * 将array_1设置为null
         */
        byte[] array_1 = new byte[1024*1024*16];
        array_1 = new byte[1024 * 1024*16];
        array_1 = new byte[1024 * 1024*16];
        array_1 = null;
        /**
         * 创建1M大小的数组
         * Eden区域内存不够发生GC
         */
        byte[] array_2 = new byte[1024 * 1024*16];
    }
}
