package com.mintlolly.leetcode;

import java.util.HashMap;

/**
 * Created on 2021/7/5
 *
 * @author jiangbo
 * Description:https://leetcode-cn.com/problems/number-of-atoms/
 *
 * ex1:
 * 输入：formula = "H2O"
 * 输出："H2O"
 * 解释：
 * 原子的数量是 {'H': 2, 'O': 1}。
 *
 * ex2:
 * 输入：formula = "Mg(OH)2"
 * 输出："H2MgO2"
 * 解释：
 * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 *
 * ex3:
 * 输入：formula = "K4(ON(SO3)2)2"
 * 输出："K4N2O14S4"
 * 解释：
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 *
 * ex4:
 * 输入：formula = "Be32"
 * 输出："Be32"
 */

public class CountOfAtoms {
    int i,n;
    String formula;
    /**
     * @param formula 化学式
     * @return
     *
     * 通用的解法是 递归 或者 栈
     *
     * 栈解法：
     */
    public HashMap<String,Integer> countOfAtoms(String formula){
        this.i = 0;
        this.n = formula.length();
        this.formula = formula;
        HashMap<String,Integer> result = new HashMap<>();
        while(i < n){
            String atom = parseAtom();
            int num = parseNum();
            result.put(atom,num);
        }
        return result;
    }

    //转换为元素
    public String parseAtom(){
        StringBuilder sb = new StringBuilder();
        sb.append(formula.charAt(i++));
        while(i < n && Character.isLowerCase(formula.charAt(i))){
            sb.append(formula.charAt(i++));
        }
        return sb.toString();
    }
    public int parseNum(){
        if(i<n && Character.isDigit(formula.charAt(i))){
            //char转int
            return formula.charAt(i++)-'0';
        }else {
            return 1;
        }
    }

    public static void main(String[] args) {
        CountOfAtoms countOfAtoms = new CountOfAtoms();
        HashMap<String,Integer> atom = countOfAtoms.countOfAtoms("Mg2H2O");
        atom.forEach((k,v) ->{
            System.out.println(k+":"+v);
        });

    }
}
