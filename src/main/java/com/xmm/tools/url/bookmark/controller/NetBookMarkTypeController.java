package com.xmm.tools.url.bookmark.controller;


import com.xmm.tools.url.bookmark.entity.NetBookMarkType;
import com.xmm.tools.common.result.R;
import com.xmm.tools.url.bookmark.service.NetBookMarkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 网址书签类型表 前端控制器v111222123
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@CrossOrigin
@RestController
@RequestMapping("/tools/net-book-mark-type")
public class NetBookMarkTypeController {
    @Autowired
    private NetBookMarkTypeService netBookMarkTypeService;
    @GetMapping("list")
    public R list(){
        List<NetBookMarkType> list = netBookMarkTypeService.list(null);
        return R.ok().data("bookMarkTypeList",list);
    }
    @PostMapping("saveBookMarkType")
    public R saveBookMarkType(@RequestBody NetBookMarkType netBookMarkType){
        netBookMarkTypeService.save(netBookMarkType);
        return R.ok();
    }
    @PutMapping("updateBookMarkTypeById")
    public R updateBookMarkTypeById(@RequestBody NetBookMarkType netBookMarkType){
        netBookMarkTypeService.updateById(netBookMarkType);
        return R.ok();
    }
    @DeleteMapping("deleteBookMarkTypeById/{id}")
    public R deleteBookMarkTypeById(@PathVariable("id") String id){
        netBookMarkTypeService.removeBookMarkTypeById(id);
        return R.ok();
    }
    @GetMapping("deleteAllBookMarkType")
    public R deleteAllBookMarkType(){
        netBookMarkTypeService.removeAllBookMarkType();
        return R.ok();
    }
}

