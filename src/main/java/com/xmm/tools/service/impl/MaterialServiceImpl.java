package com.xmm.tools.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmm.tools.entity.MaterialVO;
import com.xmm.tools.entity.query.FilterMaterial;
import com.xmm.tools.mapper.MaterialMapper;
import com.xmm.tools.service.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, MaterialVO> implements MaterialService {
    @Override
    public List<MaterialVO> getMaterialList(FilterMaterial filterMaterial) {
        return baseMapper.getMaterialList(filterMaterial);
    }
}
