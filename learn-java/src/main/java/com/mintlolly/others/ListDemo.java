package com.mintlolly.others;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Create by on jiangbo 2020/5/28 17:42
 * <p>
 * Description:
 */
public class ListDemo {

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.forEach(System.out::println);

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addFirst("b");
        linkedList.forEach(System.out::println);

       String[] params = new String[]{"aaa","bbb","ccc","ddd","eee"};


        loopPrint(params);
    }

    /**
     * 循环打印，链表，尾节点指向头部节点
     * @param params
     */
    public static void loopPrint(String[] params){
        Node<String> node = new Node<>(null, params[0], null);
        node.prev = node;
        Node<String> node1 = new Node<>(node,params[1],null);
        node.next = node1;
        for (int i = 2; i < params.length; i++) {
            node1.next = new Node<>(node,params[i],null);
            node1 = node1.next;
            if(i == params.length - 1){
                node1.next = node.prev;
            }
        }

        do {
            System.out.println(node.item);
            node = node.next;
        } while (node != null);
    }
}
