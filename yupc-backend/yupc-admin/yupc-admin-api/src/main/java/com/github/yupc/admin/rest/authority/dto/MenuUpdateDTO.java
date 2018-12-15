package com.github.yupc.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单修改dto
 *
 * @author yupc
 * @createTime 2017-12-19 10:01
 */
@Data
@ApiModel(value = "MenuUpdate", description = "菜单")
public class MenuUpdateDTO extends BaseMenuDTO implements Serializable {
    @ApiModelProperty(value = "菜单id", required = true)
    private Long id;
}
