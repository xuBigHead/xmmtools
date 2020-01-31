package com.xmm.tools.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xmm
 * @since 2019-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户手机号码
     */
    private String phone;

    /**
     * 用户地址
     */
    private String location;

    /**
     * 用户简介
     */
    private String introduction;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 会员
     */
    private Integer vip;

    /**
     * 硬币
     */
    private Integer coin;

    /**
     * 权限
     */
    private Integer authority;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最后登陆时间
     */
    private Date gmtLogin;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     *
     */
    private String bak4;

    /**
     *
     */
    private String bak5;


}
