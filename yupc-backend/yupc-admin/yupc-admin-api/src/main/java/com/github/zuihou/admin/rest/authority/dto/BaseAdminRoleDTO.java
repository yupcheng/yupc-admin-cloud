package com.github.yupc.admin.rest.authority.dto;

import lombok.Data;

/**
 * @author yupc
 * @createTime 2018-01-02 15:51
 */
@Data
public abstract class BaseAdminRoleDTO {

    /**
     * 角色名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 是否启用
     1：启用
     0：禁用
     *
     * @mbggenerated
     */
    private Boolean isEnable;

    private String description;
}
