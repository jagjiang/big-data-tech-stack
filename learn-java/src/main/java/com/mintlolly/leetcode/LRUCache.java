package com.mintlolly.leetcode;

/**
 * Created on 2021/4/25
 *
 * @author jiangbo
 * Description:运用所掌握的数据结构，设计和实现一个
 * LRU(最近最少使用）缓存机制
 * 实现LRUCache类
 *  LRUCache(int capacity)以正整数作为容量capacity初始化LRU缓存
 *  int get(int key)如果关键字key存在于缓存中，则返回关键字的值，否则返回-1
 *  void put(int key,int value)如果关键字已经存在，则变更其数据值；
 *  如果关键字不存在，则插入该组「关键字-值」。
 *  当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，
 *  从而为新的数据值留出空间。
 *
 *  进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *  https://leetcode-cn.com/problems/lru-cache/
 */
public class LRUCache {
    int capacity;
    LRUCache(int capacity){
        this.capacity = capacity;
    }
    public int get(int key){
       return -1;
    }

    public void put(int key,int value){

    }
}
