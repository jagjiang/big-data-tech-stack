package com.mintlolly.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Create by on jiangbo 2020/6/17 15:38
 * <p>
 * Description:创建redis的连接池
 */
public class JedisUtils {
    private static String host;
    private static int port;
    private static int maxTotal;
    private static int maxIdle;
    private static JedisPool jp;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("redis");
        host = rb.getString("redis.host");
        port = Integer.parseInt(rb.getString("redis.port"));
        maxTotal = Integer.parseInt(rb.getString("redis.maxTotal"));
        maxIdle = Integer.parseInt(rb.getString("redis.maxIdle"));

        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxIdle(maxIdle);
        jpc.setMaxTotal(maxTotal);

        jp = new JedisPool(jpc,host,port);

    }

    public static Jedis getJedis(){
        return jp.getResource();
    }

    public static void main(String[] args) {
        //测试是否可以连接成功
        JedisUtils.getJedis();
    }
}
