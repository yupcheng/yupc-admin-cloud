package com.github.yupc.admin.rest.authority.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yupc
 * @createTime 2018-01-02 15:54
 */
@Data
public class AdminRoleUpdateDTO extends BaseAdminRoleDTO implements Serializable {
    private Long id;

}
