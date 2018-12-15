package com.github.yupc.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单返回dto
 *
 * @author yupc
 * @createTime 2017-12-18 11:58
 */
@Data
@ApiModel(value = "Menu", description = "菜单")
public class MenuDTO extends MenuSaveDTO implements Serializable {

    @ApiModelProperty(value = "菜单id", required = true)
    private Long id;
}
