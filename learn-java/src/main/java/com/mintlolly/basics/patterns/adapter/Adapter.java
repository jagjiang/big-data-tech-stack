package com.mintlolly.basics.patterns.adapter;

/**
 * Created on 2021/7/12
 *
 * @author jiangbo
 * Description:
 */
public class Adapter {
    int convert(int homeVolt){
        int chargeVolt = homeVolt - 215;
        return chargeVolt;
    }
}
