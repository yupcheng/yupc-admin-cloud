package com.github.yupc.admin.rest.authority.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yupc
 * @createTime 2017-12-19 16:30
 */
@Data
public abstract class BaseMenuGroupDTO {
    /**
     * 菜单类型名称
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单组名称")
    protected String name;

    /**
     * 菜单类型描述
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单组描述")
    protected String description;
}
