package com.mintlolly.basics.patterns.builder;

import com.mintlolly.basics.patterns.builder.MilkTea;

/**
 * Created on 2021/4/23
 *
 * @author jiangbo
 * Description:
 */
public class User {

    private static void show(MilkTea milkTea){
        String pearl;
        String ice;
        if(milkTea.isIce()){
            ice = "加冰";
        }else{
            ice = "不加冰";
        }
        if(milkTea.isPearl()){
            pearl = "加珍珠";
        }else {
            pearl = "不加珍珠";
        }

        System.out.println("一份"+milkTea.getSize()+"、"+pearl+"、"+ice +"的"+milkTea.getType()+"奶茶");
    }
    private static void buyMilkTea(){
        MilkTea milkTea = new MilkTea.Builder("草莓味")
                .ice(true)
                .pearl(true)
                .size("超大杯")
                .build();
        show(milkTea);
    }
    public static void main(String[] args) {
        buyMilkTea();
    }
}
