package com.github.yupc.admin.repository.account.service;

import com.github.yupc.admin.entity.account.domain.AccountDO;
import com.github.yupc.admin.entity.account.po.Admin;
import com.github.yupc.admin.repository.account.example.AdminExample;
import com.github.yupc.base.service.BaseService;

/**
 * @author yupc
 * @createTime 2017-12-15 11:13
 */
public interface AdminService extends BaseService<Long, Admin, AdminExample> {
    Admin get(String userName, String passWord);

    AccountDO getAccount(String userName);
}
