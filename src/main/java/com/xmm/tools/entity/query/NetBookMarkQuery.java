package com.xmm.tools.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "书签查询对象", description = "书签查询对象封装")
public class NetBookMarkQuery {
    @ApiModelProperty(value = "书签名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "书签类型id")
    private String typeId;
}
