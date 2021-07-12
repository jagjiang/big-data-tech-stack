package com.mintlolly.basics.patterns.decorator.enhanced;

/**
 * Created on 2021/7/12
 *
 * @author jiangbo
 * Description:戒指装饰类，将Me包装起来
 */
public class RingDecorator implements IBeauty{
    private final IBeauty me;

    public RingDecorator(IBeauty me){
        this.me = me;
    }
    @Override
    public int getBeautyValue() {
        return me.getBeautyValue() + 20;
    }
}
