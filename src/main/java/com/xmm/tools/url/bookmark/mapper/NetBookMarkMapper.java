package com.xmm.tools.url.bookmark.mapper;

import com.xmm.tools.url.bookmark.entity.NetBookMark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
public interface NetBookMarkMapper extends BaseMapper<NetBookMark> {
    void saveBookMark(@Param("id") Long id, @Param("name") String name);
}
