package com.github.yupc.admin.repository.authority.dao;

import com.github.yupc.admin.entity.authority.po.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper extends com.github.yupc.base.dao.BaseDao<Long, com.github.yupc.admin.entity.authority.po.AdminRole, com.github.yupc.admin.repository.authority.example.AdminRoleExample> {
    List<AdminRole> findRole(@Param("appId") String appId, @Param("applicationId") Long applicationId);
}