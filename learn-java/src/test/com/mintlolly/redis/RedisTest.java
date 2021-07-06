package com.mintlolly.redis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Create by on jiangbo 2020/6/17 11:15
 * <p>
 * Description:
 */
public class RedisTest {
    final static Logger LOG = LoggerFactory.getLogger(com.mintlolly.redis.RedisTest.class);
    @Test
    public void connTest(){
        Jedis jedis = new Jedis("172.16.122.46", 6379);
        LOG.info("连接成功 服务正在运行:{}", jedis.ping());
    }
}
