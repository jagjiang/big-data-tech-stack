package com.mintlolly.redis;

import redis.clients.jedis.Jedis;

/**
 * Create by on jiangbo 2020/6/17 13:49
 * <p>
 * Description:使用redis控制多少秒内可以访问多少次
 */
public class RedisDemo {
    private String id;
    private int times;

    RedisDemo(String id,int times){
        this.id = id;
        this.times = times;
    }

    public void service(){
        Jedis jedis = JedisUtils.getJedis();
        String value = jedis.get("compid:"+id);
        try {
            //如果redis key不存在则创建具有指定周期的key,代码中为5秒
            if(value == null){
                jedis.setex("compid:"+id,5,Long.MAX_VALUE-times+"");
            }else {
                Long val = times -(Long.MAX_VALUE -jedis.incr("compid:"+id));
                System.out.println("用户："+id +" 5秒内操作执行第"+val+"次");
            }
        }catch (Exception e){
            System.out.println("使用次数已达到上限，请升级会员");
        }finally {
            jedis.close();
        }
    }
}

class MyThread extends Thread{
    RedisDemo redisDemo;

    MyThread(String id,int num){
        redisDemo = new RedisDemo(id,num);
    }

    @Override
    public void run() {
        while (true){
            redisDemo.service();
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Main{
    public static void main(String[] args) {
        MyThread mt1 = new MyThread("初级用户",10);
        MyThread mt2 = new MyThread("高级用户",30);
        mt1.start();
        mt2.start();
    }
}