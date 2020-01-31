package com.xmm.tools.user.service;

import com.xmm.tools.user.entity.UserAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmm.tools.common.result.R;

import javax.servlet.http.HttpServletRequest;

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
     *
     * @param userAccount
     * @param request
     * @return
     */
    R login(UserAccount userAccount, HttpServletRequest request);

    /**
     * 将生成的token和用户信息存储到缓存中
     * @param token
     * @param userAccountFromDb
     */
    void addTokenAndUserInfoToCache(String token, UserAccount userAccountFromDb);

    R info(String token);
}
