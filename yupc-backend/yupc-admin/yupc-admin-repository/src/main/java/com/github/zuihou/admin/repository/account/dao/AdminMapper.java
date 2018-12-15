package com.github.yupc.admin.repository.account.dao;

import com.github.yupc.admin.entity.account.domain.AccountDO;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends com.github.yupc.base.dao.BaseDao<Long, com.github.yupc.admin.entity.account.po.Admin, com.github.yupc.admin.repository.account.example.AdminExample> {
    AccountDO getAccount(@Param("userName") String userName);
}