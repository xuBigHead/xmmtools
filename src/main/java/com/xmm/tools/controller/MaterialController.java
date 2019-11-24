package com.xmm.tools.controller;

import com.xmm.tools.entity.MaterialVO;
import com.xmm.tools.entity.query.FilterMaterial;
import com.xmm.tools.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    MaterialService materialService;
    @GetMapping("/list")
    public List<MaterialVO> getMaterialList(@RequestBody FilterMaterial filterMaterial){
        System.err.println(filterMaterial);
        return materialService.getMaterialList(filterMaterial);
    }
}
