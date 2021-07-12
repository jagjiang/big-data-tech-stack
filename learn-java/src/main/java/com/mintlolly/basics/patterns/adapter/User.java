package com.mintlolly.basics.patterns.adapter;

/**
 * Created on 2021/7/12
 *
 * @author jiangbo
 * Description:
 */
public class User {
    public static void main(String[] args) {
        HomeBattery homeBattery = new HomeBattery();
        USBLine usbLine = new USBLine();
        int homeVolt = homeBattery.supply();
        System.out.println("家庭电源电压是:"+homeVolt+"V");
        //不经过调整直接充电
        usbLine.charge(homeVolt);
        //家庭电压通过适配器进行调整
        Adapter adapter = new Adapter();
        int chargeVolt = adapter.convert(homeVolt);
        System.out.println("适配器将电压转换成:"+chargeVolt+"V");
        //充电
        usbLine.charge(chargeVolt);
    }
}
