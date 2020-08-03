package com.mintlolly.others;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

/**
 * Create by on jiangbo 2020/7/30 14:56
 * <p>
 * Description:typesafe的配置库工具类Config试用
 */
public class ConfigTest {
    @Test
    public void test(){
        Config conf = ConfigFactory.load();

    }
}
