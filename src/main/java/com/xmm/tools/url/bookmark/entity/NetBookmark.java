package com.xmm.tools.url.bookmark.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xmm
 * @since 2019-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("net_bookmark")
public class NetBookmark implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 书签id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 书签名称
     */
    private String netName;

    /**
     * 书签url地址
     */
    private String netUrl;

    /**
     * 书签状态
     */
    private Integer netStatus;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 书签类型id
     */
    private String typeId;

    /**
     * 排序
     */
    private Integer sort;

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


}
