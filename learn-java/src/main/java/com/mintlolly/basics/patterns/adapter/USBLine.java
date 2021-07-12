package com.mintlolly.basics.patterns.adapter;

/**
 * Created on 2021/7/12
 *
 * @author jiangbo
 * Description:
 */
public class USBLine {
    void charge(int volt){
        if(volt != 5) throw new IllegalArgumentException("只能接收5V电压");
        System.out.println("正常充电");
    }
}
