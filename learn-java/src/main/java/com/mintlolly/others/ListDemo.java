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

        String e = "aaa";
        Node<String> node = new Node<>(null, e, null);
        System.out.println(node.next);
    }
}
