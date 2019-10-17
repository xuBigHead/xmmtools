package com.xmm.tools.controller;


import com.sun.net.httpserver.Authenticator;
import com.xmm.tools.entity.UserAccount;
import com.xmm.tools.result.R;
import com.xmm.tools.service.UserAccountService;
import com.xmm.tools.utils.JwtUtil;
import com.xmm.tools.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.misc.PostVMInitHook;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 用户表 前端控制器v111222
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@Api(description = "用户登录管理")
@RestController
@CrossOrigin
@RequestMapping("/tools/user")
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;
    @Value("${onlineTeaching.default.avatar}")
    private String avatar;
    @Value("${practice.sso-private-key}")
    private String key;

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public R login(@RequestBody UserAccount userAccount, HttpServletRequest request){
        UserAccount userAccountFromDb = userAccountService.login(userAccount);
        if(userAccountFromDb != null){
            String ip = request.getRemoteAddr();
            HashMap<String, Object> userInfoMap = new HashMap<>();
            userInfoMap.put("username", userAccountFromDb.getUsername());
            userInfoMap.put("id", userAccountFromDb.getId());
            String token = JwtUtil.encode(key, userInfoMap, ip);
            userAccountService.addTokenAndUserInfoToCache(token,userAccountFromDb);
            return R.ok().data("token",token);
        }else {
            return R.error().message("用户名和密码不匹配");
        }
    }
    //根据token获取用户信息
    @ApiOperation(value = "获取用户信息")
    @GetMapping("info")
    public R info(String token){
        return R.ok()
                .data("roles","[\"admin\"]")
                .data("name","admin")
                .data("avatar",avatar);
    }
    @ApiOperation(value = "用户退出登录")
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}

