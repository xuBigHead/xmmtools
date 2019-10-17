package com.xmm.tools.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmm.tools.entity.NetBookMark;
import com.xmm.tools.entity.NetHistoryRecord;
import com.xmm.tools.mapper.NetHistoryRecordMapper;
import com.xmm.tools.service.NetHistoryRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xmm
 * @since 2019-09-16
 */
@Service
public class NetHistoryRecordServiceImpl extends ServiceImpl<NetHistoryRecordMapper,
        NetHistoryRecord> implements NetHistoryRecordService {
    @Override
    public void pageByViewTime(Page<NetHistoryRecord> netHistoryRecordPage) {
        QueryWrapper<NetHistoryRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        // todo 查询结果中，历史记录名称长度不能大于45且不能等于0
        baseMapper.selectPage(netHistoryRecordPage, queryWrapper);
    }
}
