package com.mintlolly.others;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;

/**
 * Create by on jiangbo 2020/5/20 13:16
 * <p>
 * Description:
 */
public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>(5);


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

        hashMap.forEach((k,v)-> System.out.println(k+":"+v));

        //跟踪源码，了解红黑树在HashMap中的应用
//        HashMap<String, Integer> rbtree = new HashMap<>();
        HashMap<Integer,Integer> num = new HashMap<>();
        HashMap<String,String>  samehashcode = new HashMap<>();
        int samecode = 0;
        int i = 1;
        while (true){
            //随机生成4个字符的字符串
            String s = RandomStringUtils.randomAlphanumeric(4);
//            rbtree.put(uuid,i);

            //获取hashcode值相同的字符串
            int code = s.hashCode();
            if(num.get(code) != null){
                int count = num.get(code)+1;
                num.put(code,count);
                String codenum = code+"_"+count;
                samehashcode.put(codenum,s);
                if(num.get(code)>= 10){
                    samecode = code;
                    break;
                }
            } else {
                num.put(code,1);
                samehashcode.put(code+"_"+1,s);
            }
            i++;
        }
        for (int j = 1; j < 11; j++) {
            System.out.println(samecode+"_"+j+ ":" + samehashcode.get(samecode+"_"+j));
        }
    }
}
