package com.mintlolly.algorithm.sort;

import java.util.Random;

/**
 * Create by on jiangbo 2020/6/8 10:13
 * <p>
 * Description:排序算法效率的比较
 */
public class SortCompare {

    private static Random random = new Random();
    /**
     * 算法所用时间
     * @param args 算法类型
     * @param a 排序数据输入
     * @return  所用时间
     * 1.7后switch 支持String
     */
    public static double time(String args,Comparable[] a){
        long start = System.currentTimeMillis();
        switch (args){
            case "Selection":
                SortDemo.selectSort(a);
                break;
            case "Insertion":
                SortDemo.insertSort(a);
                break;
            default:
                break;
        }
        long end = System.currentTimeMillis();

        return (end-start)/1000.0;
    }

    /**
     *
     * @param arg 排序算法类型
     * @param N   排序的数组大小
     * @param T   测试排序的次数
     * @return    用时
     */
    public static double timeRandomInput(String arg,int N,int T){
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = random.nextDouble();
            }
            total+= time(arg,a);
        }
        return total;
    }

    public static void main(String[] args) {
        String name1 = "Insertion";
        String name2 = "Selection";
        int N = 10000;
        int T = 20;
        double t1 = timeRandomInput(name1,N,T);
        double t2 = timeRandomInput(name2,N,T);
        System.out.format("%s use %.1fs,%s use %.1fs",name1,t1,name2,t2);
    }
}
