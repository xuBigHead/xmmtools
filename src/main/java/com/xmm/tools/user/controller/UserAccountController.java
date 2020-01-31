package com.xmm.tools.user.controller;


import com.xmm.tools.common.result.R;
import com.xmm.tools.user.entity.UserAccount;
import com.xmm.tools.user.service.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xmm
 * @since 2019-09-10
 */
@RestController
@CrossOrigin
@RequestMapping("/tools/user")
@AllArgsConstructor
public class UserAccountController {
    UserAccountService userAccountService;

    @PostMapping("/login")
    public R login(@RequestBody UserAccount userAccount,HttpServletRequest request){
        return userAccountService.login(userAccount, request);
    }

    @GetMapping("/info")
    public R info(String token){
        return userAccountService.info(token);
    }

    @PostMapping("/logout")
    public R logout(){
        return R.ok();
    }
}

