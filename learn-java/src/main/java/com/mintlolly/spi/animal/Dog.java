package com.mintlolly.spi.animal;

import com.mintlolly.spi.IShout;

/**
 * Create by on jiangbo 2020/7/28 13:56
 * <p>
 * Description:
 */
public class Dog implements IShout {
    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}
