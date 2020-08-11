package com.mintlolly.others;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Create by on jiangbo 2020/6/9 9:45
 * 参考: https://www.cnblogs.com/zhuyeshen/p/10956822.html
 * Description:
 * fail-fast机制是java集合(Collection)中的一种错误机制。当多个线程对同一个集合的内容进行操作时，就可能会产生fail-fast事件。
 * 异常：ConcurrentModificationException
 * ArrayList随机查询速度比LinkedList块
 * 通过get(i)即可获得相应内存中存放的值。
 * LinkedList是一个双向链表，链表只能顺序访问，LinkedList中的get方法是按照顺序从列表的一端开始检查，直到找到要找的地址。
 *
 *
 * 随机访问 arraylist比较占优势
 * 对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据
 */
public class ArrayListDemo {
    public static void main(String[] args)  {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iter = list.iterator();
        //hasNext源码 cursor != size 返回true，证明还有元素
        // cursor表示下一个要访问的元素的索引，第一次循环时，cursor为0不等于size
        // 如果不做remove操作，cursor等于size时，遍历结束

        //第一遍循环cursor变为了1，而当remove元素后，size变为了0，cursor != size 依然成立
        //还会证明有元素，而实际上已经没有元素了，
        //在next时checkForComodification检查时就会报异常
        while(iter.hasNext()){
            Integer integer = iter.next();
            if(integer==2) {
                //错误的方法
//                list.remove(integer);
                //正确的方法使用iterator的remove方法，对数据进行移除
                iter.remove();
            }
        }
        list.forEach(f -> System.out.println(f));
    }
}
