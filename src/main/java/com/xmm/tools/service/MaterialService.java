package com.xmm.tools.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xmm.tools.entity.MaterialVO;
import com.xmm.tools.entity.query.FilterMaterial;

import java.util.List;

public interface MaterialService extends IService<MaterialVO> {
    List<MaterialVO> getMaterialList(FilterMaterial filterMaterial);
}
