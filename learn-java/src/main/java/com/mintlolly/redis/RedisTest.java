package com.mintlolly.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author jiangbo
 * @description
 * @date 2019/11/26 18:22
 */
public class RedisTest {
    final static Logger logger = LoggerFactory.getLogger(RedisTest.class);

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        logger.info("连接成功 服务正在运行:{}",jedis.ping());
    }
}
