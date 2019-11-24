package com.xmm.tools.entity.query;

import lombok.Data;

import java.util.Date;

@Data
public class FilterMaterial {
    private String keyword;
    private Long classifyId;
    private Integer type;
    private Date beginTime = new Date("1969-03-05 00:00:00");
    private Date endTime;
    private Integer page;

    private Integer pageSize;
}
