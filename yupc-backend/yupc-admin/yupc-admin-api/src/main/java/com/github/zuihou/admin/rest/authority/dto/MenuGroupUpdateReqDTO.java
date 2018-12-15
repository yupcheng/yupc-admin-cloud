package com.github.yupc.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yupc
 * @createTime 2017-12-19 16:31
 */
@Data
@ApiModel(value = "MenuGroupUpdate", description = "菜单组")
public class MenuGroupUpdateReqDTO extends BaseMenuGroupDTO {
    @ApiModelProperty(value = "菜单组id", required = true)
    private Long id;

}
