package com.mintlolly.basics.patterns.decorator.enhanced;

/**
 * Created on 2021/7/12
 *
 * @author jiangbo
 * Description:耳环装饰类
 */
public class EarringDecorator implements IBeauty{
    private final IBeauty me;
    public EarringDecorator(IBeauty me){
        this.me = me;
    }


    @Override
    public int getBeautyValue() {
        return me.getBeautyValue() + 50;
    }
}
