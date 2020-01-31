package com.xmm.tools.url.bookmark.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmm.tools.url.bookmark.entity.NetBookmark;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
public interface NetBookmarkMapper extends BaseMapper<NetBookmark> {
    void saveBookmark(@Param("id") Long id, @Param("name") String name);
}