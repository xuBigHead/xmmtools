/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package com.xmm.tools.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 视图实体类
 *
 * @author xmm
 * @since 2019-11-19
 */
@Data
public class MaterialVO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private Date updateTime;

    private Integer type;

    private Integer refCounter;

    @ApiModelProperty(value = "素材分类名称")
    private String classify;

    @ApiModelProperty(value = "素材作者名称")
    private String authorName;

    @ApiModelProperty(value = "素材引用范围")
    private List refRange;
}
