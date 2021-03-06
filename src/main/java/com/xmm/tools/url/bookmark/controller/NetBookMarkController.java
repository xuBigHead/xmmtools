package com.xmm.tools.url.bookmark.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmm.tools.common.result.R;
import com.xmm.tools.url.bookmark.entity.NetBookmark;
import com.xmm.tools.url.bookmark.entity.query.NetBookMarkQuery;
import com.xmm.tools.url.bookmark.service.NetBookMarkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器v11122222点18分123
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@CrossOrigin
@RestController
@RequestMapping("/tools/net-book-mark")
@AllArgsConstructor
public class NetBookMarkController {
    private NetBookMarkService netBookMarkService;

    @PostMapping("{page}/{limit}")
    public R getNetBookMarkByPage(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit,
            @RequestBody NetBookMarkQuery query){
        Page<NetBookmark> netBookMarkPage = new Page<>(page, limit);
        if (query == null) {
            netBookMarkService.page(netBookMarkPage,null);
        }else{
            netBookMarkService.bookMarkByPage(netBookMarkPage, query);
        }
        long total = netBookMarkPage.getTotal();
        List<NetBookmark> records = netBookMarkPage.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    @PostMapping("saveBookMark")
    public R saveBookMark(@RequestBody NetBookmark bookMark){
        netBookMarkService.saveBookMark(bookMark);
        return R.ok();
    }

    @PostMapping("/save")
    public R save(@RequestBody NetBookmark bookMark){
        return netBookMarkService.saveBookmark(bookMark);
    }
    @GetMapping("getBookMarkById/{id}")
    public R getBookMarkById(@PathVariable("id") String id){
        NetBookmark netBookMark = netBookMarkService.getById(id);
        return R.ok().data("netBookMark",netBookMark);
    }

    @PutMapping("updateBookMarkById")
    public R updateBookMarkById(@RequestBody NetBookmark bookMark){
        try {
            netBookMarkService.updateById(bookMark);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

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

    @PostMapping("importBookMarkFile")
    public R importBookMarkFile(@RequestParam("file") MultipartFile file){
        netBookMarkService.importBookMarkFile(file);
        return R.ok();
    }
}

