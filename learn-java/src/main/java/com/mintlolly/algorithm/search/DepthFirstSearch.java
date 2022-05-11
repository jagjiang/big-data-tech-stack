package com.mintlolly.algorithm.search;

import java.util.Stack;

/**
 * Created on 2022/2/21
 *
 * @author jiangbo
 * Description:深度优先遍历 前序遍历
 */
public class DepthFirstSearch {
    public static void main(String[] args) {
        CreateTree createTree = new CreateTree();
        Node tree = createTree.createTree();

        dfs(tree);
        System.out.println();
        stackDfs(tree);
        int s = 100;
        char[] chars = String.valueOf(s).toCharArray();

        System.out.println(addDigits(38));
    }

    public static int addDigits(int num) {
        while(num > 9){
            char[] chars = String.valueOf(num).toCharArray();
            num = 0;
            for(int i = 0;i< chars.length;i++){
                num += chars[i];
            }
        }
        return num;
    }
    //方法一：递归
    private static void dfs(Node node){
        if(null == node){
            return;
        }
        System.out.print(node.getValue()+",");
        dfs(node.getLeft());
        dfs(node.getRight());
    }
    //方法二：使用栈来实现
    private static void stackDfs(Node node){
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        // 先把根节点压栈
        stack.push(node);
        while (!stack.isEmpty()) {
            Node treeNode = stack.pop();
            // 遍历节点
            System.out.print(treeNode.getValue()+",");

            // 先压右节点
            if (treeNode.getRight() != null) {
                stack.push(treeNode.getRight());
            }

            // 再压左节点
            if (treeNode.getLeft() != null) {
                stack.push(treeNode.getLeft());
            }
        }
    }
}
