package com.mintlolly.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @author jiangbo
 * @description
 * @date 2019/11/26 17:57
 */
public class LogTest {
    final static Logger logger  = LoggerFactory.getLogger(LogTest.class);
    public static void main(String[] args) throws InterruptedException {
        while(true){
            logger.info("这是一条slf4j的测试");

            SecureRandom sr = null;
            try {
                sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
            } catch (NoSuchAlgorithmException e) {
                logger.error("获取高强度安全随机数失败，开始获取普通安全随机数");
                sr = new SecureRandom(); // 获取普通的安全随机数生成器
            }
            byte[] buffer = new byte[16];
            sr.nextBytes(buffer); // 用安全随机数填充buffer
            logger.info("获取的随机数为：{}", Arrays.toString(buffer));

            try {
                Integer.parseInt("abc");
            } catch (Exception e) {
                logger.error("这是一条错误测试："+ e.getMessage(), e);
            } finally {
                logger.info("finally:output end");
            }

            Thread.sleep(10000);
        }

    }
}
