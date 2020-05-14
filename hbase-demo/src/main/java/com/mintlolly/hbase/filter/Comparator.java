package com.mintlolly.hbase.filter;

import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

public class Comparator {
    /**
     * 二进制比较器，用于按字典顺序比较指定字节数组
     * 按位比较
     * bbb > aaa 1
     * bbb = bbb 0
     * bbb < ccc -1
     * bbb < bbf -4    b c d e f  asc码差值4
     * 参与比较的字符一样 字符串长度不一样，则返回字符串长度的差值
     */
    public static void BinaryComparatorDemo() {
        BinaryComparator bc = new BinaryComparator(Bytes.toBytes("bbb"));
        int code1 = bc.compareTo(Bytes.toBytes("aaa"), 0, 3);
        System.out.println(code1);
        int code2 = bc.compareTo(Bytes.toBytes("bbb"), 0, 3);
        System.out.println(code2);
        int code3 = bc.compareTo(Bytes.toBytes("ccc"), 0, 3);
        System.out.println(code3);
        int code4 = bc.compareTo(Bytes.toBytes("bbf"), 0, 3);
        System.out.println(code4);
        int code5 = bc.compareTo(Bytes.toBytes("bbbaaa"), 0, 8);
        System.out.println(code5);
        int code6 = bc.compareTo(Bytes.toBytes("bbbaaa"), 0, 9);
        System.out.println(code6);
    }

    /**
     * 二进制比较器，只比较前缀是否与指定字节数组相同
     * bbb = bbb 0
     * bbb < ccc -1
     * bbb < bbf -4    b c d e f  asc码差值4
     * bbb与bbbaaa前面相同  返回的值为0
     */
    public static void BinaryPrefixComparatorDemo() {
        BinaryPrefixComparator bc = new BinaryPrefixComparator(Bytes.toBytes("bbb"));
        int code1 = bc.compareTo(Bytes.toBytes("aaa"), 0, 3);
        System.out.println(code1);
        int code2 = bc.compareTo(Bytes.toBytes("bbb"), 0, 3);
        System.out.println(code2);
        int code3 = bc.compareTo(Bytes.toBytes("ccc"), 0, 3);
        System.out.println(code3);
        int code4 = bc.compareTo(Bytes.toBytes("bbf"), 0, 3);
        System.out.println(code4);
        int code5 = bc.compareTo(Bytes.toBytes("bbbaaa"), 0, 8);
        System.out.println(code5);
        int code6 = bc.compareTo(Bytes.toBytes("bbbaaa"), 0, 9);
        System.out.println(code6);
    }

    /**
     * 位比价器，通过BitwiseOp提供的AND（与）、OR（或）、NOT（非）进行比较。返回结果要么为1要么为0，仅支持 EQUAL 和非 EQUAL。
     * 仅支持等于和不等于，没有大小之分
     * 相等为0 不等为1
     */
    public static void BitComparatorDemo() {
        // 长度相同按位或比较：由低位起逐位比较，每一位按位或比较都为0，则返回1，否则返回0。
        BitComparator bc1 = new BitComparator(new byte[]{0, 0, 0, 0}, BitComparator.BitwiseOp.OR);
        int i = bc1.compareTo(new byte[]{0, 0, 0, 0}, 0, 4);
        System.out.println(i);
        // 长度相同按位与比较：由低位起逐位比较，每一位按位与比较都为0，则返回1，否则返回0。
        BitComparator bc2 = new BitComparator(new byte[]{1, 0, 1, 0}, BitComparator.BitwiseOp.AND);
        int j = bc2.compareTo(new byte[]{0, 1, 0, 1}, 0, 4);
        System.out.println(j);
        // 长度相同按位异或比较：由低位起逐位比较，每一位按位异或比较都为0，则返回1，否则返回0。
        BitComparator bc3 = new BitComparator(new byte[]{1, 0, 1, 0}, BitComparator.BitwiseOp.XOR);
        int x = bc3.compareTo(new byte[]{1, 0, 1, 0}, 0, 4);
        System.out.println(x);
        // 长度不同，返回1，否则按位比较
        BitComparator bc4 = new BitComparator(new byte[]{1, 0, 1, 0}, BitComparator.BitwiseOp.XOR);
        int y = bc4.compareTo(new byte[]{1, 0, 1}, 0, 3);
        System.out.println(y);
    }

    /**
     * Long 型专用比较器，返回值：0 -1 1
     * 0 相等
     * -1 小
     * 1 大
     */
    public static void LongComparatorDemo() {
        LongComparator longComparator = new LongComparator(1000L);
        int i = longComparator.compareTo(Bytes.toBytes(1000L), 0, 8);
        System.out.println(i);
        int i2 = longComparator.compareTo(Bytes.toBytes(1001L), 0, 8);
        System.out.println(i2);
        int i3 = longComparator.compareTo(Bytes.toBytes(998L), 0, 8);
        System.out.println(i3);
    }

    /**
     * 控制比较式，判断当前值是不是为null。是null返回0，不是null返回1，仅支持 EQUAL 和非 EQUAL。
     */
    public static void NullXomparatorDemo() {
        NullComparator nc = new NullComparator();
        int i1 = nc.compareTo(Bytes.toBytes("abc"));
        int i2 = nc.compareTo(Bytes.toBytes(""));
        int i3 = nc.compareTo(null);
        System.out.println(i1); // 1
        System.out.println(i2); // 1
        System.out.println(i3); // 0
    }

    /**
     * 提供一个正则的比较器，支持正则表达式的值比较，仅支持 EQUAL 和非 EQUAL。匹配成功返回0，匹配失败返回1。
     */
    public static void RegexStringComparator() {
        RegexStringComparator rsc = new RegexStringComparator("abc");
        int abc = rsc.compareTo(Bytes.toBytes("abcd"), 0, 3);
        System.out.println(abc); // 0
        int bcd = rsc.compareTo(Bytes.toBytes("bcd"), 0, 3);
        System.out.println(bcd); // 1

        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        RegexStringComparator rsc2 = new RegexStringComparator(check);
        int code = rsc2.compareTo(Bytes.toBytes("zpb@163.com"), 0, "zpb@163.com".length());
        System.out.println(code); // 0
        int code2 = rsc2.compareTo(Bytes.toBytes("zpb#163.com"), 0, "zpb#163.com".length());
        System.out.println(code2); // 1
    }

    /**
     * 判断提供的子串是否出现在value中，并且不区分大小写。包含字串返回0，不包含返回1，仅支持 EQUAL 和非 EQUAL。
     */
    public static void SubstringComparatorDemo(){

    }
    public static void main(String[] args) {
//        BinaryComparatorDemo();
//        BinaryPrefixComparatorDemo();
//        BitComparatorDemo();
//        LongComparatorDemo();
//        NullXomparatorDemo();
//        RegexStringComparator();
//        SubstringComparatorDemo();
    }

}
