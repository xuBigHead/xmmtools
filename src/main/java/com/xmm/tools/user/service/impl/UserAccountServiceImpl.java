package com.xmm.tools.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmm.tools.common.result.R;
import com.xmm.tools.user.consts.UserAccountConst;
import com.xmm.tools.user.entity.UserAccount;
import com.xmm.tools.user.mapper.UserAccountMapper;
import com.xmm.tools.user.service.UserAccountService;
import com.xmm.tools.user.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@Service
@AllArgsConstructor
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {
    StringRedisTemplate redisTemplate;

    @Override
    public R login(UserAccount userAccount, HttpServletRequest request) {
        UserAccount userAccountFromDb = baseMapper.selectOne(
                Wrappers.<UserAccount>query().lambda()
                        .eq(UserAccount::getUsername, userAccount.getUsername())
                        .eq(UserAccount::getPassword, userAccount.getPassword()));
        if(userAccountFromDb != null){
            String ip = request.getRemoteAddr();
            HashMap<String, Object> userInfoMap = new HashMap<>();
            userInfoMap.put("username", userAccountFromDb.getUsername());
            userInfoMap.put("id", userAccountFromDb.getId());
            String token = JwtUtil.encode(UserAccountConst.KEY, userInfoMap, ip);
            this.addTokenAndUserInfoToCache(token,userAccountFromDb);
            return R.ok().data("token",token);
        }else {
            return R.error().message("用户名和密码不匹配");
        }
    }

    @Override
    public void addTokenAndUserInfoToCache(String token, UserAccount userAccountFromDb) {
        String tokenKey = "user:"+userAccountFromDb.getId()+":token";
        String userKey = "user:"+userAccountFromDb.getId()+":info";

        redisTemplate.opsForValue().set(tokenKey, token, 60*60, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(userKey, JSON.toJSONString(userAccountFromDb),60*60, TimeUnit.SECONDS);
    }

    @Override
    public R info(String token) {
        return R.ok()
                .data("roles","[\"admin\"]")
                .data("name","admin")
                .data("avatar",UserAccountConst.AVATAR);
    }
}
