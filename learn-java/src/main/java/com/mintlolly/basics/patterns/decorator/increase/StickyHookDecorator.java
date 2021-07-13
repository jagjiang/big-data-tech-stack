package com.mintlolly.basics.patterns.decorator.increase;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class StickyHookDecorator implements IStickyHookHouse{
    private final IHouse house;

    StickyHookDecorator(IHouse house){
        this.house = house;
    }
    @Override
    public void live() {
        house.live();
    }

    @Override
    public void hangThings() {
        System.out.println("有了粘钩后，新增了挂东西功能");
    }
}
