package com.mintlolly.leetcode.easy;

/**
 * Created on 2021/7/23
 *
 * @author jiangbo
 * Description: 检查是否区域内所有整数都被覆盖
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 *
 *
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * 输出：false
 * 解释：21 没有被任何一个区间覆盖。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsCovered {
//  错误示例 【【2，3】【4，5】【9，10】】  8->9  8并没有在区间中
    public boolean isCovered(int[][] ranges,int left,int right){
        boolean leftCover = false,rightCover = false;
        for (int[] range : ranges) {
            if (range[0] < left || leftCover) {
                leftCover = true;
            }
            if (range[1] > right || rightCover) {
                rightCover = true;
            }
        }
        return rightCover && leftCover;
    }

    public static void main(String[] args) {
        int[][] ranges =new int[][] {{1,2},{3,4},{5,6}};
        System.out.println(new IsCovered().isCovered(ranges,2,5));
    }
}
