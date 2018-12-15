package com.github.yupc.admin.rest.authority.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yupc
 * @createTime 2018-01-02 15:51
 */
@Data
public class AdminRoleDTO extends BaseAdminRoleDTO implements Serializable {
    private Long id;

    /**
     * 角色编码
     *
     * @mbggenerated
     */
    private String code;
}
