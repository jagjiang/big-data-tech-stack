package com.mintlolly.algorithm;

/**
 * Created on 2021/7/31
 *
 * @author jiangbo
 * Description:递归
 */
public class GetMax {
    public static int process(int[] arr,int left,int right){
        if(left == right){
            return arr[left];
        }
        int mid = left + ((right -left) >> 1); //5 / 2 = 2  0+2=2 mid=2
        int leftMax = process(arr,left,mid);   // 0,2
        int rightMax = process(arr,mid+1,right);
        return Math.max(leftMax,rightMax);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,5,7,3,2,5,8};
        int process = process(arr, 0, 5);
        System.out.println(process);
    }
}
