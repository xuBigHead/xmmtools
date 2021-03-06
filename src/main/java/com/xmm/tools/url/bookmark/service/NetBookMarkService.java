package com.xmm.tools.url.bookmark.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmm.tools.common.result.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmm.tools.url.bookmark.entity.NetBookmark;
import com.xmm.tools.url.bookmark.entity.query.NetBookMarkQuery;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
public interface NetBookMarkService extends IService<NetBookmark> {
    /**
     * 根据书签类型id和书签名称来查询书签
     * @param netBookMarkPage
     * @param query
     */
    void bookMarkByPage(Page<NetBookmark> netBookMarkPage, NetBookMarkQuery query);
    /**
     * 删除书签类型时删除该类型下所有的书签
     * @param typeId
     */
    void removeBookMarkOfType(String typeId);
    /**
     * 根据导入的书签文件来添加书签
     * @param file
     */
    void importBookMarkFile(MultipartFile file);

    /**
     * 删除所有书签
     */
    void removeAllBookMark();

    /**
     * 添加书签并将该书签类型的amount属性加一
     * @param bookMark
     */
    void saveBookMark(NetBookmark bookMark);
    /**
     * 删除书签并将该书签类型的amount属性减一
     * @param id
     */
    void removeBookMarkById(String id);

    /**
     * 添加新的书签
     * @param bookMark          书签对象
     * @return                  添加状态
     */
    R saveBookmark(NetBookmark bookMark);
}
