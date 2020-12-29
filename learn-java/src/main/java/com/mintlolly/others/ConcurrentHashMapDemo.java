package com.mintlolly.others;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by on jiangbo 2020/8/7 14:05
 * <p>
 * Description:https://www.jianshu.com/p/a7767e6ff2a2
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
//        HashMap<String,String> hashMap  = new HashMap<>();
        ConcurrentHashMap<String,String> hashMap = new ConcurrentHashMap<>();
        hashMap.put("name","xiaoming");
        hashMap.put("age","22");
        hashMap.put("addr","beijing");



        hashMap.forEach((k,v) -> {
            if(k.equals("name")){
                hashMap.remove(k);
            }else{
                System.out.println(k+":"+v);
            }
        });
    }
}
