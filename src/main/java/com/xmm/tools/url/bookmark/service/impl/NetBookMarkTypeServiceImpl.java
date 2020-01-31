package com.xmm.tools.url.bookmark.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmm.tools.url.bookmark.entity.NetBookMarkType;
import com.xmm.tools.url.bookmark.mapper.NetBookMarkTypeMapper;
import com.xmm.tools.url.bookmark.service.NetBookMarkService;
import com.xmm.tools.url.bookmark.service.NetBookMarkTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网址书签类型表 服务实现类
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@Service
public class NetBookMarkTypeServiceImpl extends ServiceImpl<NetBookMarkTypeMapper, NetBookMarkType> implements NetBookMarkTypeService {
    @Autowired
    NetBookMarkService bookMarkService;
    @Override
    public void removeBookMarkTypeById(String id) {
        // 删除该id的书签类型
        baseMapper.deleteById(id);
        // 删除属于该书签类型下的所有的书签
        bookMarkService.removeBookMarkOfType(id);
    }

    @Override
    public void removeAllBookMarkType() {
        QueryWrapper<NetBookMarkType> queryWrapper = new QueryWrapper<>();
        // 根据user_id删除所有的书签类型及该类型下的书签
        queryWrapper.isNull("user_id");
        baseMapper.delete(queryWrapper);
        bookMarkService.removeAllBookMark();
    }
}
