package com.github.yupc.admin.repository.account.service;

import com.github.yupc.admin.entity.account.po.Applications;
import com.github.yupc.admin.repository.account.example.ApplicationsExample;
import com.github.yupc.base.service.BaseService;

/**
 * @author yupc
 * @createTime 2017-12-15 11:08
 */
public interface ApplicationsService extends BaseService<Long, Applications, ApplicationsExample> {

    Applications getBySecret(String appId, String appSecret);

    Applications saveApp(Applications app);
}
