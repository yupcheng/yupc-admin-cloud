package com.github.yupc.admin.repository.authority.service.impl;

import com.github.yupc.admin.entity.authority.po.AdminMenuGroup;
import com.github.yupc.admin.repository.authority.dao.AdminMenuGroupMapper;
import com.github.yupc.admin.repository.authority.example.AdminMenuGroupExample;
import com.github.yupc.admin.repository.authority.service.AdminMenuGroupService;
import com.github.yupc.base.dao.BaseDao;
import com.github.yupc.base.service.impl.BaseServiceImpl;
import com.github.yupc.commons.constant.DeleteStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yupc
 * @createTime 2018-01-02 22:28
 */
@Service
@Slf4j
public class AdminMenuGroupServiceImpl extends BaseServiceImpl<Long, AdminMenuGroup, AdminMenuGroupExample> implements AdminMenuGroupService {
    @Autowired
    private AdminMenuGroupMapper adminMenuGroupMapper;

    @Override
    protected BaseDao<Long, AdminMenuGroup, AdminMenuGroupExample> getDao() {
        return adminMenuGroupMapper;
    }

    @Override
    public boolean check(String appId, String code) {
        AdminMenuGroupExample example = new AdminMenuGroupExample();
        example.createCriteria().andAppIdEqualTo(appId).andCodeEqualTo(code)
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        if (super.count(example) > 0) {
            return true;
        }
        return false;
    }
}
