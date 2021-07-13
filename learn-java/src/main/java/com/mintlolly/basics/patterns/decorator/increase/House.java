package com.mintlolly.basics.patterns.decorator.increase;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class House implements IHouse{
    @Override
    public void live() {
        System.out.println("房屋原有的功能：居住功能");
    }
}
