package com.github.yupc.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 资源保存数据 请求参数
 *
 * @author yupc
 * @createTime 2017-12-19 11:18
 */
@Data
@ApiModel(value = "ResourceSave", description = "资源")
public class ResourceSaveReqDTO extends BaseResourceDTO implements Serializable {
    /**
     * 菜单编码
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源编码", required = true)
    private String code;
    /**
     * 父菜单
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源所属菜单id", required = true)
    private Long menuId;
}
