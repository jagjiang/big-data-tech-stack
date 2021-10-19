package com.mintlolly.leetcode.medium;

/**
 * Created on 2021/10/19
 *
 * @author jiangbo
 * Description:前缀树
 *  前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Trie {
    //指向子节点的指针数组children,数组长度为26，即小写英文字母的数量。
    private Trie[] children;
    //布尔字段isEnd,表示该节点是否为字符串结尾
    private boolean isEnd;

    public Trie(){
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word){
        //指向当前对象的引用，本对象自己
        Trie node = this;
        //将单词按照字符顺序添加到链表当中
        for(int i = 0;i < word.length();i++){
            char ch = word.charAt(i);
            int index = ch - 'a';
            if(node.children[index] == null){
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    private Trie searchPrefix(String prefix){
        Trie node = this;
        for(int i = 0; i < prefix.length();i++){
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if(node.children[index] == null){
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    public  boolean startsWith(String prefix){
        return searchPrefix(prefix) != null;
    }

    public boolean search(String word){
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("appl"));
        System.out.println(trie.startsWith("appl"));
        System.out.println(trie);
    }
}
