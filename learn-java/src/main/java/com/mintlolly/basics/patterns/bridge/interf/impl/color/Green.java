package com.mintlolly.basics.patterns.bridge.interf.impl.color;

import com.mintlolly.basics.patterns.bridge.interf.IColor;

/**
 * Created on 2021/4/26
 *
 * @author jiangbo
 * Description:
 */
public class Green implements IColor {
    @Override
    public String getColor() {
        return "绿色";
    }
}
