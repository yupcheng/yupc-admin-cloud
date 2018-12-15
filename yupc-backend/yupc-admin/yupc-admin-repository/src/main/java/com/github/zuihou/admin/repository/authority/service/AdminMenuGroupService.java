package com.github.yupc.admin.repository.authority.service;

import com.github.yupc.admin.entity.authority.po.AdminMenuGroup;
import com.github.yupc.admin.repository.authority.example.AdminMenuGroupExample;
import com.github.yupc.base.service.BaseService;

/**
 * @author yupc
 * @createTime 2018-01-02 22:28
 */
public interface AdminMenuGroupService extends BaseService<Long, AdminMenuGroup, AdminMenuGroupExample> {
    boolean check(String appId, String code);
}
