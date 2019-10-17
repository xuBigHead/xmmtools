package com.xmm.tools.service;

import com.xmm.tools.entity.UserAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
public interface UserAccountService extends IService<UserAccount> {

    /**
     * 登陆用户名密码校验
     * @param userAccount
     * @return
     */
    UserAccount login(UserAccount userAccount);

    /**
     * 将生成的token和用户信息存储到缓存中
     * @param token
     * @param userAccountFromDb
     */
    void addTokenAndUserInfoToCache(String token, UserAccount userAccountFromDb);
}
