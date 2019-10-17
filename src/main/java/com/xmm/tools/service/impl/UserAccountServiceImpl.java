package com.xmm.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmm.tools.entity.UserAccount;
import com.xmm.tools.mapper.UserAccountMapper;
import com.xmm.tools.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmm.tools.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {
    @Autowired
    RedisUtil redisUtil;
    @Override
    public UserAccount login(UserAccount userAccount) {
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userAccount.getUsername());
        queryWrapper.eq("password",userAccount.getPassword());
        UserAccount userAccountFromDb = baseMapper.selectOne(queryWrapper);
        Jedis jedis = redisUtil.getJedis();
        String ping = jedis.ping();
        System.err.println(ping);
        return userAccountFromDb;
    }

    @Override
    public void addTokenAndUserInfoToCache(String token, UserAccount userAccountFromDb) {
        String tokenKey = "user:"+userAccountFromDb.getId()+":token";
        String userKey = "user:"+userAccountFromDb.getId()+":info";
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            jedis.setex(tokenKey,60*60,token);
            jedis.setex(userKey,60*60, JSON.toJSONString(userAccountFromDb));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
