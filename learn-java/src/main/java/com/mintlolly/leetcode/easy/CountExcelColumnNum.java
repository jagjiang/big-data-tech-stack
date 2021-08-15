package com.mintlolly.leetcode.easy;

/**
 * Created on 2021/7/30
 *
 * @author jiangbo
 * Description:
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountExcelColumnNum {
    public static int titleToNumber(String columnTitle) {
        //A 1 AA 26+1=27  AZ=26+26=32 BA = 26*2+1=33
        //26^n+char
        int multiple = 1;
        int num = 0;
        for (int i = columnTitle.length() - 1; i >= 0 ; i--) {
            int k = columnTitle.charAt(i) - 'A' + 1;
            num += k*multiple;
            multiple *= 26;
        }
        return num;
    }

    public static void main(String[] args) {
        int i = titleToNumber("ABCDEFG");
        System.out.println(i);
    }
}
