package com.mintlolly.spi;

import java.util.ServiceLoader;

/**
 * Create by on jiangbo 2020/7/28 14:09
 * <p>
 * Description:
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);

        for (IShout shout : shouts) {
            shout.shout();
        }
    }
}
