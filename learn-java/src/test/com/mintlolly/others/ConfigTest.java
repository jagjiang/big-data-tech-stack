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
    public void test() {
        Config conf = ConfigFactory.load();

    }

    /**
     * 如果当前匹配成功的 case 语句块没有 break 语句，
     * 则从当前 case 开始，后续所有 case 的值都会输出，如果后续的 case 语句块有 break 语句则会跳出判断。
     */
    @Test
    public void caseTest() {
        switch ('c'){
            default:
                System.out.println("default");
            case 'a':
                System.out.println("a");
                break;
            case 'b':
                System.out.println("b");
                break;
        }

    }

    @Test
    public void compareToTest(){
        String s = "abc123";
        String s1 = new String("abc123");
        System.out.println(s.compareTo(s1));
        System.out.println(s==s1);
        System.out.println(s.equals(s1));
    }
}
