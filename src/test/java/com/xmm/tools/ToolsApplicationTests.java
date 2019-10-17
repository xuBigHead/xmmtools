package com.xmm.tools;

import com.xmm.tools.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolsApplicationTests {
    @Autowired
    RedisUtil redisUtil;
    @Test
    public void contextLoads() {
        String tokenKey = "user:"+":token";
        String userKey = "user:"+":info";
        Jedis jedis = null;
        jedis = redisUtil.getJedis();
        String ping = jedis.ping();
        System.err.println(ping);
        //jedis.setex(tokenKey,60*60,userKey);
        jedis.close();
    }
}
