package com.mintlolly.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Create by on jiangbo 2021/2/26 15:13
 * <p>
 * Description:学习一些思想
 */
public class SolutionThought {

    /**
     * 输入：
     * words = ["aaaa","asas","able","ability","actt","actor","access"],
     * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
     * 输出：[1,1,3,2,4,0]
     * 解释：
     * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
     * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
     * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
     * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
     * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
     * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
     *
     * 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param words
     * @param puzzles
     * @return
     */
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


}
