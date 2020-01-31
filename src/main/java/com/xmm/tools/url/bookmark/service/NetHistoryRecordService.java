package com.xmm.tools.url.bookmark.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmm.tools.url.bookmark.entity.NetHistoryRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xmm
 * @since 2019-09-16
 */
public interface NetHistoryRecordService extends IService<NetHistoryRecord> {

    void pageByViewTime(Page<NetHistoryRecord> netHistoryRecordPage);
}
