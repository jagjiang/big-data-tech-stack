package com.mintlolly.basics.patterns.bridge;

import com.mintlolly.basics.patterns.bridge.interf.impl.shape.Circle;
import com.mintlolly.basics.patterns.bridge.interf.impl.color.Green;
import com.mintlolly.basics.patterns.bridge.interf.impl.shape.Rectangel;
import com.mintlolly.basics.patterns.bridge.interf.impl.color.Red;

/**
 * Created on 2021/4/26
 *
 * @author jiangbo
 * Description:桥接模式主类
 */
public class Draw {
    public static void main(String[] args) {
        Rectangel rectangel = new Rectangel();
        rectangel.setColor(new Red());
        rectangel.draw();

        Circle circle = new Circle();
        circle.setColor(new Green());
        circle.draw();
    }
}
