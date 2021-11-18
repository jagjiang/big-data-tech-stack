package com.mintlolly.algorithm.sort;

/**
 * Create by on jiangbo 2020/6/4 16:42
 * <p>
 * Description:
 * 选择排序
 * 插入排序
 * 归并排序
 * 快排
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
    private static void show(int[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
    }

    public static boolean isSorted(int[] a){
        for (int i = 0; i < a.length; i++) {
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }

    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        //左序列指针
        int i = left;
        //右序列指针
        int j = mid + 1;
        //临时数组指针
        int t = 0;
        //当 左序指针 <= mid 并且右续指针 < right 则需要归并
        while ( i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        //不满足上面条件 则会走下面其中一条路径
        //左序遍历追加
        while (i <= mid){
            temp[t++] = arr[i++];
        }
        //右序遍历追加
        while (j <= right){
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素拷贝到原数组中
        while (left <= right){
            arr[left++] = temp[t++];
        }
    }

    static int n = 1;
    public static void main(String[] args) {
        int[] a = {9,1,6,4,2,20,10};
        int[] temp = new int[a.length];
//        selectSort(a);
//        insertSort(a);
//        mergeSort(a,0,a.length-1,temp);
        quickSort(a,0,a.length - 1);
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

    /**
     * 归并排序
     *
     */
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        //如果left < right 则递归继续进行
        if(left < right){
            int mid = (left+right)/2;
            //左边归并排序，使得左子序列有序
            mergeSort(arr,left,mid,temp);
            //右边归并排序，使得右子序列有序
            mergeSort(arr,mid+1,right,temp);
            //将两个有序子数组合并操作
            merge(arr,left,mid,right,temp);
        }
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            n++;
            quickSort(arr, left, partitionIndex - 1);
            n = n+100;
            quickSort(arr, partitionIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(n+":"+i + ":" + arr[i]);
        }
        //最后把基准数据放回数组中
        swap(arr, pivot, index - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(n+":"+i + ":" + arr[i]);
        }
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
