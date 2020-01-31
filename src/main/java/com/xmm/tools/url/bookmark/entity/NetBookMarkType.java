package com.xmm.tools.url.bookmark.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 网址书签类型表
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class NetBookMarkType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 书签类型
     */
    private String type;

    /**
     * 该书签类型包含书签数量
     */
    private Integer amount;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 排序
     */
    private Integer sort;

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


}
