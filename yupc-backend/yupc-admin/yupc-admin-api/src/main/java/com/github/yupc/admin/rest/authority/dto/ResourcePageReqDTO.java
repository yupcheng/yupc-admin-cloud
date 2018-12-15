package com.github.yupc.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 资源分页数据 请求参数
 *
 * @author yupc
 * @createTime 2017-12-18 14:02
 */
@Data
@ApiModel(value = "ResourcePageReq", description = "资源")
public class ResourcePageReqDTO implements Serializable {
    /**
     * 菜单编码
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源编码", required = true)
    private String code;
    /**
     * 菜单名称
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源名称")
    private String name;

    /**
     * 父菜单
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源所属菜单id", required = true)
    private Long menuId;
}
