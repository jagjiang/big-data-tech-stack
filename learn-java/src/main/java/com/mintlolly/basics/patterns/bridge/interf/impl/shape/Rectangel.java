package com.mintlolly.basics.patterns.bridge.interf.impl.shape;

import com.mintlolly.basics.patterns.bridge.interf.IColor;
import com.mintlolly.basics.patterns.bridge.interf.IShape;

/**
 * Created on 2021/4/26
 *
 * @author jiangbo
 * Description:
 */
public class Rectangel implements IShape {
    private IColor color;
    public void setColor(IColor color){
        this.color = color;
    }
    @Override
    public void draw() {
        System.out.println("绘制"+color.getColor()+"长方形");
    }
}
