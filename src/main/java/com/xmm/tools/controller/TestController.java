package com.xmm.tools.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.xmm.tools.entity.NetBookMark;
import com.xmm.tools.mapper.NetBookMarkMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@AllArgsConstructor
public class TestController {
    NetBookMarkMapper netBookMarkMapper;
    @GetMapping("/test")
    public void save(){
        long id = IdWorker.getId();
        String name = "success";
        netBookMarkMapper.saveBookMark(id,name);
    }
}
