package com.github.yupc.admin.rest.authority.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yupc
 * @createTime 2018-01-02 15:53
 */
@Data
public class AdminRoleSaveDTO extends BaseAdminRoleDTO implements Serializable {
    /**
     * 角色编码
     *
     * @mbggenerated
     */
    private String code;
}
