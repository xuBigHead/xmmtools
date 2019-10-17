package com.xmm.tools.service;

import com.xmm.tools.entity.NetBookMarkType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网址书签类型表 服务类
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
public interface NetBookMarkTypeService extends IService<NetBookMarkType> {

    /**
     * 根据id删除书签类型和该类型下所有书签
     * @param id
     */
    void removeBookMarkTypeById(String id);

    /**
     * 删除所有书签类型及书签
     */
    void removeAllBookMarkType();
}
