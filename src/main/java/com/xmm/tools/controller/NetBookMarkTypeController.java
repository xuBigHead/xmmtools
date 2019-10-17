package com.xmm.tools.controller;


import com.xmm.tools.entity.NetBookMarkType;
import com.xmm.tools.result.R;
import com.xmm.tools.service.NetBookMarkTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 网址书签类型表 前端控制器
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@Api(description = "书签类型管理")
@CrossOrigin
@RestController
@RequestMapping("/tools/net-book-mark-type")
public class NetBookMarkTypeController {
    @Autowired
    private NetBookMarkTypeService netBookMarkTypeService;
    @ApiOperation("获取书签类型列表")
    @GetMapping("list")
    public R list(){
        List<NetBookMarkType> list = netBookMarkTypeService.list(null);
        return R.ok().data("bookMarkTypeList",list);
    }
    @ApiOperation("添加书签类型")
    @PostMapping("saveBookMarkType")
    public R saveBookMarkType(@RequestBody NetBookMarkType netBookMarkType){
        netBookMarkTypeService.save(netBookMarkType);
        return R.ok();
    }
    @ApiOperation("根据id修改书签类型")
    @PutMapping("updateBookMarkTypeById")
    public R updateBookMarkTypeById(@RequestBody NetBookMarkType netBookMarkType){
        netBookMarkTypeService.updateById(netBookMarkType);
        return R.ok();
    }
    @ApiOperation("根据id删除书签类型")
    @DeleteMapping("deleteBookMarkTypeById/{id}")
    public R deleteBookMarkTypeById(@PathVariable("id") String id){
        netBookMarkTypeService.removeBookMarkTypeById(id);
        return R.ok();
    }
    @ApiOperation("删除所有书签")
    @GetMapping("deleteAllBookMarkType")
    public R deleteAllBookMarkType(){
        netBookMarkTypeService.removeAllBookMarkType();
        return R.ok();
    }
}

