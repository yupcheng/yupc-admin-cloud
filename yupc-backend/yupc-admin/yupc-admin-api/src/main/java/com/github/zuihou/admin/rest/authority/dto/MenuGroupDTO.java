package com.github.yupc.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yupc
 * @createTime 2017-12-19 16:30
 */
@Data
@ApiModel(value = "MenuGroup", description = "菜单组")
public class MenuGroupDTO extends BaseMenuGroupDTO {
    /**
     * 菜单类型编码
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单组编码", required = true)
    private String code;

    @ApiModelProperty(value = "菜单组id", required = true)
    private Long id;
}
