package com.xmm.tools.url.bookmark.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmm.tools.url.bookmark.entity.NetHistoryRecord;
import com.xmm.tools.common.result.R;
import com.xmm.tools.url.bookmark.service.NetHistoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器v111222
 * </p>
 *
 * @author xmm
 * @since 2019-09-16
 */
@CrossOrigin
@RestController
@RequestMapping("/tools/net-history-record")
public class NetHistoryRecordController {
    @Autowired
    NetHistoryRecordService historyRecordService;
    @PostMapping("setHistoryRecordFromTempermonkey")
    public R setHistoryRecordFromTempermonkey(
            @RequestBody NetHistoryRecord historyRecordJson) {
        historyRecordService.save(historyRecordJson);
        return R.ok();
    }

    @PostMapping("{page}/{limit}")
    public R getHistoryRecordByPage(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit) {
        Page<NetHistoryRecord> netHistoryRecordPage = new Page<>(page, limit);
        historyRecordService.pageByViewTime(netHistoryRecordPage);
        long total = netHistoryRecordPage.getTotal();
        List<NetHistoryRecord> records = netHistoryRecordPage.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
}

