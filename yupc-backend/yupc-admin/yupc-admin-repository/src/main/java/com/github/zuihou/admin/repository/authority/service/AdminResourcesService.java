package com.github.yupc.admin.repository.authority.service;

import com.github.yupc.admin.entity.authority.po.AdminResources;
import com.github.yupc.admin.repository.authority.example.AdminResourcesExample;
import com.github.yupc.base.service.BaseService;

import java.util.List;

/**
 * @author yupc
 * @createTime 2017-12-15 11:15
 */
public interface AdminResourcesService extends BaseService<Long, AdminResources, AdminResourcesExample> {
    /**
     * 检查code是否存在
     *
     * @param appId appid
     * @param code  菜单编码
     * @return 存在返回true 不存在返回false
     */
    boolean check(String appId, String code);

    /**
     * 检测当前菜单是否存在子菜单和子资源
     *
     * @param appId
     * @param id    菜单id
     * @return 存在返回true 不存在返回false
     */
    boolean checkChildren(String appId, Long id);

    /**
     * 检查指定菜单组id下是否存在菜单
     *
     * @param appId
     * @param menuGroupId
     * @return
     */
    boolean checkMenu(String appId, Long menuGroupId);

    List<AdminResources> findResources(String appId);

    List<AdminResources> findMenu(String appId);
}
