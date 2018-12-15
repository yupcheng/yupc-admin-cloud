package com.github.yupc.admin.rest.account.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yupc
 * @createTime 2018-01-02 15:44
 */
@Data
public class ApplicationsUpdateDTO extends BaseApplicationsDTO implements Serializable {
    private Long id;

}
