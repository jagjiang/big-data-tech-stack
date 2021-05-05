package com.mintlolly.algorithm;

import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Create by on jiangbo 2021/2/17 16:43
 * <p>
 * Description:
 */
public class Solution {

    //重塑矩阵
    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        int count = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            count += nums[i].length;
            for (int i1 = 0; i1 < nums[i].length; i1++) {
                arrayList.add(nums[i][i1]);
            }
        }
        if (count != r * c) {
            return nums;
        }
        int[][] result = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                result[i][j] = arrayList.get(i * c + j);
            }
        }
        return result;
    }

    /**
     * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
     * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips
     * <p>
     * 示例 1：
     * 输入：A = [0,1,0], K = 1
     * 输出：2
     * 解释：先翻转 A[0]，然后翻转 A[2]。
     * <p>
     * 示例 2：
     * 输入：A = [1,1,0], K = 2
     * 输出：-1
     * 解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
     * <p>
     * 示例 3：
     * 输入：A = [0,0,0,1,0,1,1,0], K = 3
     * 输出：3
     * 解释：
     * 翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
     * 翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
     * 翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
     * <p>
     * 提示：
     * 1 <= A.length <= 30000
     * 1 <= K <= A.length
     */
    public static int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= K && A[i - K] > 1) {
                revCnt ^= 1;
                A[i - K] -= 2; // 复原数组元素，若允许修改数组 A，则可以省略
            }
            if (A[i] == revCnt) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                revCnt ^= 1;
                A[i] += 2;
            }
        }
        return ans;
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;

        while (l1 != null || l2 != null) {

            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;

            int sum = n1 + n2 + carry;

            if (null == head) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }


    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int total = 0;
        int n = customers.length;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }

        //从开始计算可以增加多少
        int increase = 0;
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }
        //窗口滑动
        int max = increase;
        for (int i = X; i < n; i++) {
            increase = increase - customers[i - X] * grumpy[i - X] + customers[X] * grumpy[X];
            max = Math.max(max, increase);
        }
        return total + max;

    }


    public static int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static void print(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j]);
            }
            System.out.println();
        }
    }

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        HashMap<Integer, Integer> frequency = new HashMap<>();

        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                mask |= 1 << ch - 'a';
            }
//            int i = Integer.bitCount(mask);
            frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
        }

        List<Integer> ans = new ArrayList();
        for (String puzzle : puzzles) {
            int total = 0;

            int mask = 0;
            for (int i = 1; i < puzzle.length(); ++i) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }

            int subset = mask;
            /**
             * 第一遍
             * puzzle   abde    11011
             * mask     bde     11010
             * subset   bde     11010
             * s        abde    11011
             *
             * 第二遍
             * subset-1         11001
             * &mask            11010
             * subset           11000
             * s        ade     11001
             *
             * 第三遍
             */
            do {
                //确保第一个元素永远存在
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                //所有子集
                subset = (subset - 1) & mask;
            } while (subset != mask);

            ans.add(total);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] nums = {{1, 2, 3, 7}, {4, 2, 2, 5}, {4, 3, 2, 4}};
//        int[][] ints = matrixReshape(nums, 4, 3);
//

//
//        int[] A = {1, 0, 1, 0, 0, 0, 0};
//        int i = minKBitFlips(A, 3);
//        System.out.println(i);

//        ListNode listNode = addTwoNumbers(new ListNode(1, new ListNode(2)), new ListNode(9, new ListNode(9, new ListNode(9))));
//        System.out.println(listNode.val + "," + listNode.next.val);

//        int[] c = {4,10,10};
//        int[] g = {1,1,0};
        //
//        int i = maxSatisfied(c, g, 2);
//        System.out.println(i);

//        int[][] transpose = transpose(nums);
//        print(transpose);

        //猜字谜
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};

        findNumOfValidWords(words, puzzles).forEach(integer -> System.out.println(integer));
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
