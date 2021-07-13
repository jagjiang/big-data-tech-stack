package com.mintlolly.basics.patterns.decorator.increase;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class Client {
    public static void main(String[] args) {
        IHouse house = new House();
        house.live();
        IStickyHookHouse stickyHookHouse = new StickyHookDecorator(house);
        stickyHookHouse.live();
        stickyHookHouse.hangThings();
    }
}
