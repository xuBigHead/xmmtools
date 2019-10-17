package com.xmm.tools.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmm.tools.entity.NetBookMark;
import com.xmm.tools.entity.query.NetBookMarkQuery;
import com.xmm.tools.result.R;
import com.xmm.tools.service.NetBookMarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器v111222
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@Api(description = "书签管理")
@CrossOrigin
@RestController
@RequestMapping("/tools/net-book-mark")
public class NetBookMarkController {
    @Autowired
    private NetBookMarkService netBookMarkService;
    @ApiOperation("分页查询")
    @PostMapping("{page}/{limit}")
    public R getNetBookMarkByPage(
            @ApiParam(name="page", value = "当前页码", required = true)
            @PathVariable("page") Integer page,
            @ApiParam(name="limit", value = "每页显示记录数" ,required = true)
            @PathVariable("limit") Integer limit,
            @ApiParam(name="query", value = "查询条件" ,required = false)
            @RequestBody NetBookMarkQuery query){
        Page<NetBookMark> netBookMarkPage = new Page<>(page, limit);
        if (query == null) {
            netBookMarkService.page(netBookMarkPage,null);
        }else{
            netBookMarkService.bookMarkByPage(netBookMarkPage, query);
        }
        long total = netBookMarkPage.getTotal();
        List<NetBookMark> records = netBookMarkPage.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
    @ApiOperation("添加新书签")
    @PostMapping("saveBookMark")
    public R saveBookMark(@RequestBody NetBookMark bookMark){
        netBookMarkService.saveBookMark(bookMark);
        return R.ok();
    }
    @ApiOperation("根据id获取书签信息")
    @GetMapping("getBookMarkById/{id}")
    public R getBookMarkById(@PathVariable("id") String id){
        NetBookMark netBookMark = netBookMarkService.getById(id);
        return R.ok().data("netBookMark",netBookMark);
    }
    @ApiOperation("根据id修改书签信息")
    @PutMapping("updateBookMarkById")
    public R updateBookMarkById(@RequestBody NetBookMark bookMark){
        try {
            netBookMarkService.updateById(bookMark);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    @ApiOperation("根据id删除书签信息")
    @DeleteMapping("deleteBookMarkById/{id}")
    public R deleteBookMarkById(@PathVariable("id") String id){
        try {
            netBookMarkService.removeBookMarkById(id);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    @ApiOperation("导入书签列表文件")
    @PostMapping("importBookMarkFile")
    public R importBookMarkFile(@RequestParam("file") MultipartFile file){
        netBookMarkService.importBookMarkFile(file);
        return R.ok();
    }
}

