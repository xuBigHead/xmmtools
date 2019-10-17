package com.xmm.tools.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmm.tools.entity.NetHistoryRecord;
import com.xmm.tools.result.R;
import com.xmm.tools.service.NetHistoryRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(description = "历史记录管理")
@CrossOrigin
@RestController
@RequestMapping("/tools/net-history-record")
public class NetHistoryRecordController {
    @Autowired
    NetHistoryRecordService historyRecordService;
    @ApiOperation("将历史记录存入数据库")
    @PostMapping("setHistoryRecordFromTempermonkey")
    public R setHistoryRecordFromTempermonkey(
            @ApiParam()
            @RequestBody NetHistoryRecord historyRecordJson) {
        historyRecordService.save(historyRecordJson);
        return R.ok();
    }

    @ApiOperation("获取历史记录并返回到前端服务器")
    @PostMapping("{page}/{limit}")
    public R getHistoryRecordByPage(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Integer page,
            @ApiParam(name = "limit", value = "每页显示记录数", required = true)
            @PathVariable("limit") Integer limit) {
        Page<NetHistoryRecord> netHistoryRecordPage = new Page<>(page, limit);
        historyRecordService.pageByViewTime(netHistoryRecordPage);
        long total = netHistoryRecordPage.getTotal();
        List<NetHistoryRecord> records = netHistoryRecordPage.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
}

