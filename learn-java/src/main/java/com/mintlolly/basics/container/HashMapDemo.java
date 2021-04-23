package com.mintlolly.basics.container;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

/**
 * Create by on jiangbo 2020/5/20 13:16
 * <p>
 * Description:
 */
public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>(5);

        //获取n个相同的hashcode对应的string
        getSameHashCode(20);
        //我的理解,hash碰撞到第8次就会到treeifyBin,只是如果长度不到64的时候只会扩容
        hashMap.put("z3qn","1");
        hashMap.put("yT3n","2");
        hashMap.put("xs3n","3");
        hashMap.put("yT50","4");
        hashMap.put("xs4O","5");
        hashMap.put("z3rO","6");
        hashMap.put("yRs0","7");
        hashMap.put("xrRn","8");

        hashMap.put("fsoV","9");
        hashMap.put("gUPV","10");
        hashMap.put("h5p7","11");
        hashMap.put("h6Q7","12");
        hashMap.put("fu27","13");
        hashMap.put("ftOu","14");
        hashMap.put("h71V","15");
        hashMap.put("h6Ou","16");
        hashMap.put("gV1V","17");

        hashMap.put("RPPu","18");
        hashMap.put("RQ2V","19");
        hashMap.put("S0pV","20");
        hashMap.put("S1Pu","21");
        hashMap.put("Qp37","22");
        hashMap.put("RQ37","23");
        hashMap.put("S1QV","24");
        hashMap.put("S1R7","25");

        hashMap.put("wQnu","26");
        hashMap.put("wRQ7","27");
        hashMap.put("x40u","28");
        hashMap.put("x40u","29");
        hashMap.put("wS27","30");
        hashMap.put("x427","31");
        hashMap.put("wRPV","32");
        hashMap.put("vpp7","33");
        hashMap.put("x2p7","34");
        hashMap.put("x2nu","35");

        System.out.println("排序前的hashmap:");
        hashMap.forEach((k,v)-> System.out.println(k+":"+v));


        //hashmap根据value排序
        System.out.println("根据key排序后的hashmap:");
        sortByKey(hashMap);
        hashMap.forEach((k,v)-> System.out.println(k+":"+v));

        System.out.println("根据value排序后的hashmap:");
        ArrayList<Map.Entry<String, String>> entries = sortByValue(hashMap);
        entries.forEach(map-> System.out.println(map.getKey()+":"+map.getValue()));
    }

    //获取n个hashcode值相同的字符串
    public static HashMap<String,String> getSameHashCode(Integer n){
        //用来存放hashcode以及出现的次数
        HashMap<Integer,Integer> num = new HashMap<>();
        //相同hashcode的字符串
        HashMap<String,String> stringHashMapSameHashCode = new HashMap<>();
        //hashCode的值
        int samecode = 0;
        int i = 1;
        while (true){
            //随机生成4个字符的字符串
            String s = RandomStringUtils.randomAlphanumeric(4);
            //获取hashcode值相同的字符串
            int code = s.hashCode();
            if(num.get(code) != null){
                int count = num.get(code)+1;
                num.put(code,count);
                String codenum = code+"_"+count;
                stringHashMapSameHashCode.put(codenum,s);
                if(num.get(code)>= n){
                    samecode = code;
                    break;
                }
            } else {
                num.put(code,1);
                stringHashMapSameHashCode.put(code+"_"+1,s);
            }
            i++;
        }
        for (int j = 1; j < n; j++) {
            System.out.println(samecode+"_"+j+ ":" + stringHashMapSameHashCode.get(samecode+"_"+j));
        }

        return stringHashMapSameHashCode;
    }
    public static HashMap<String,String> sortByKey(HashMap<String,String> hashMap){

        return null;
    }

    public static ArrayList<Map.Entry<String, String>> sortByValue(HashMap<String,String> hashMap){
        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (Integer.parseInt(o1.getValue()) - Integer.parseInt(o2.getValue()));
            }
        });
        return list;
    }
}
