package com.xmm.tools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmm.tools.entity.MaterialVO;
import com.xmm.tools.entity.query.FilterMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialMapper extends BaseMapper<MaterialVO> {
    List<MaterialVO> getMaterialList(@Param("filter") FilterMaterial filterMaterial);
}
