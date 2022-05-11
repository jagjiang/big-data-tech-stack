package com.mintlolly.leetcode.medium;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created on 2021/4/25
 * https://blog.csdn.net/angellee1988/article/details/121062194
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
public class LRUCache extends LinkedHashMap<Integer,Integer> {
    private int capacity;
    LRUCache(int capacity){
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    public Integer get(Object key) {
        return super.get(key);
    }

    @Override
    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        for (int i = 0; i < 9; i++) {
            lruCache.put(i,i);
            lruCache.get(0);
            lruCache.forEach((k,v) -> System.out.println(k+","+v));
        }

    }
}
