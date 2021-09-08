package com.mintlolly.leetcode.easy;

/**
 * Created on 2021/9/7
 *
 * @author jiangbo
 * Description:
 * 示例 1:
 * 输入：s = "RLRRLLRLRL"
 * 输出：4
 * 解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 *
 * 示例 2：
 * 输入：s = "RLLLLRRRLR"
 * 输出：3
 * 解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BalancedStringSplit {
    public int balancedStringSplit(String s){
        int ans = 0,d = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'L'){
                d++;
            }else{
                d--;
            }
            if(0 == d)
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int ans = new BalancedStringSplit().balancedStringSplit("RLRRLLRLRL");
        System.out.println(ans);
    }
}
