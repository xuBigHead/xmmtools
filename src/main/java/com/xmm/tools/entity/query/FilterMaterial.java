package com.xmm.tools.entity.query;

import lombok.Data;

import java.util.Date;

@Data
public class FilterMaterial {
    private String keyword;
    private Long classifyId;
    private Integer type;
    private Date beginTime;
    private Date endTime;
    private Integer page;
    private Integer pageSize;
}
