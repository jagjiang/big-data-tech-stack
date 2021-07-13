package com.mintlolly.basics.patterns.decorator.enhanced;

/**
 * Created on 2021/7/12
 *
 * @author jiangbo
 * Description:
 */
public class Client {
    public static void main(String[] args) {
        IBeauty me = new Me();
        System.out.println("颜值:"+me.getBeautyValue());
        //随意挑选装饰
        IBeauty meWithNecklace = new NecklaceDecorator(me);
        System.out.println("颜值:"+meWithNecklace.getBeautyValue());

        //多次装饰
        IBeauty meWithMoreDecorators = new NecklaceDecorator(new RingDecorator(new EarringDecorator(me)));
        System.out.println("戴上耳环、戒指、项链后，我的颜值:"+meWithMoreDecorators.getBeautyValue());

        //任意搭配装饰
        IBeauty meWithNecklaceAndRing = new NecklaceDecorator(new RingDecorator(me));
        System.out.println("戴上戒指、项链后，我的颜值:"+meWithNecklaceAndRing.getBeautyValue());

    }
}
