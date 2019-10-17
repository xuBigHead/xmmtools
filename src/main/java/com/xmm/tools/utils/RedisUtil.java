package com.xmm.tools.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private JedisPool jedisPool;
    public void initPool(String host,int port ,int database,String password){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //在指定时刻通过pool能够获取到的最大的连接的jedis个数
        poolConfig.setMaxTotal(200);
        //最大能够保持idle的数量，控制一个pool最多有多少个状态为idle的jedis实例
        poolConfig.setMaxIdle(30);
        poolConfig.setBlockWhenExhausted(true);
        //当连接池内的连接耗尽时，getBlockWhenExhausted为true时，连接会阻塞，
        //超过了阻塞的时间（设定的maxWaitMillis，单位毫秒）时会报错
        poolConfig.setMaxWaitMillis(10*1000);
        //在borrow一个jedis实例时，是否提前进行validate操作；
        // 如果为true，则得到的jedis实例均是可用的；默认是false
        poolConfig.setTestOnBorrow(true);
        jedisPool=new JedisPool(poolConfig,host,port,20*1000,password);
    }
    public Jedis getJedis(){
        return jedisPool.getResource();
    }
}
