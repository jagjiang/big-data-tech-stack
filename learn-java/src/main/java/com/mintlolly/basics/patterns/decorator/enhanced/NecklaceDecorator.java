package com.mintlolly.basics.patterns.decorator.enhanced;

/**
 * Created on 2021/7/12
 *
 * @author jiangbo
 * Description:项链装饰类
 */
public class NecklaceDecorator implements IBeauty{
    private final IBeauty me;
    NecklaceDecorator(IBeauty me){
        this.me = me;
    }
    @Override
    public int getBeautyValue() {
        return me.getBeautyValue() + 80;
    }
}
