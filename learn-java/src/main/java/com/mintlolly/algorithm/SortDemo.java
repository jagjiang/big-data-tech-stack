package com.mintlolly.algorithm;

/**
 * Create by on jiangbo 2020/6/4 16:42
 * <p>
 * Description:
 * 选择排序
 * 插入排序
 *
 */
public class SortDemo {
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a,int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Double[] a = {1.0,2.0,4.0,2.2,2.1};
//        selectSort(a);
        insertSort(a);
        assert isSorted(a);
        show(a);

    }


    /**
     * 选择排序
     * 逻辑：先选出最小元素，然后最小元素和第一个元素交换
     * 选第二小元素，第二小的元素和第二个位置的数据交换位置
     * 以此类推
     *
     * 运行时间和输入无关
     * 数据移动是最少的
     */
    public static void selectSort(Comparable[] a){
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i; j < len; j++) {
                if (less(a[j],a[min])) {
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }

    /**
     * 插入排序，将元素插入已经有序的适当的位置
     * 为了要给插入的元素腾出空间，将其余所有元素在插入前都向右移动一位
     *
     * 逻辑：从第二个元素开始，向左一次比对，
     * 一个一个的往后移动，后一个比前一个小，就把前一个元素右移一位
     *
     * 优化：要大幅提高速度，只需要在内循环中将较大的元素都向右移动，而不总是交换两个元素
     * 这样访问数据的次数就能减半。
     * 不需要交换的插入排序。在插入排序的实现中使较大元素右移一位只需要访问一次数组，而不是用exch
     *
     * 运行时间取决于输入中元素的初始顺序
     */
    public static void insertSort(Comparable[] a){
        int len = a.length;
        for (int i = 0; i < len; i++) {
            for (int j = i ; j > 0 && less(a[j],a[j-1]) ; j--) {
                exch(a,j,j-1);
            }
        }
    }
}
