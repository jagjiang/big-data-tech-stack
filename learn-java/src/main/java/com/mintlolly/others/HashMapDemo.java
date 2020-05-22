package com.mintlolly.others;

import java.util.HashMap;

/**
 * Create by on jiangbo 2020/5/20 13:16
 * <p>
 * Description:
 */
public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>(5);
        hashMap.put("name","ming");
        hashMap.put("age","23");
        hashMap.put("sex","male");
        hashMap.put("addr","beijing");
        hashMap.put("birthday","1212");
        hashMap.forEach((k,v)-> System.out.println(k+":"+v));
    }
}
