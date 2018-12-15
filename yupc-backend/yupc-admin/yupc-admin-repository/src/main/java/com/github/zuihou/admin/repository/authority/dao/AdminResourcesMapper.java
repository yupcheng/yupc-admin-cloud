package com.github.yupc.admin.repository.authority.dao;

import com.github.yupc.admin.constant.ResourcesType;
import com.github.yupc.admin.entity.authority.po.AdminResources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminResourcesMapper extends com.github.yupc.base.dao.BaseDao<Long, com.github.yupc.admin.entity.authority.po.AdminResources, com.github.yupc.admin.repository.authority.example.AdminResourcesExample> {
    /**
     * 查询子菜单和子资源数量
     *
     * @param appId
     * @param id
     * @return
     */
    int countChildren(@Param("appId") String appId, @Param("id") Long id);

    int checkMenu(@Param("appId") String appId, @Param("menuGroupId") Long menuGroupId);

    List<AdminResources> findResourcesByApplicationId(@Param("appId") String appId,
                                                      @Param("menuGroupCode") String menuGroupCode,
                                                      @Param("resourcesTypeList") List<ResourcesType> resourcesTypeList);
}