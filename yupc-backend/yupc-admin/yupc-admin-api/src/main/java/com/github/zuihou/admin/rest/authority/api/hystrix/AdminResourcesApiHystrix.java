package com.github.yupc.admin.rest.authority.api.hystrix;

import com.github.pagehelper.PageInfo;
import com.github.yupc.admin.rest.authority.api.AdminResourcesApi;
import com.github.yupc.admin.rest.authority.dto.MenuDTO;
import com.github.yupc.admin.rest.authority.dto.MenuGroupDTO;
import com.github.yupc.admin.rest.authority.dto.MenuGroupSaveReqDTO;
import com.github.yupc.admin.rest.authority.dto.MenuGroupUpdateReqDTO;
import com.github.yupc.admin.rest.authority.dto.MenuSaveDTO;
import com.github.yupc.admin.rest.authority.dto.MenuTreeDTO;
import com.github.yupc.admin.rest.authority.dto.MenuUpdateDTO;
import com.github.yupc.admin.rest.authority.dto.ResourceDTO;
import com.github.yupc.admin.rest.authority.dto.ResourcePageReqDTO;
import com.github.yupc.admin.rest.authority.dto.ResourceSaveReqDTO;
import com.github.yupc.admin.rest.authority.dto.ResourceUpdateReqDTO;
import com.github.yupc.base.Result;
import com.github.yupc.page.plugins.openapi.OpenApiReq;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yupc
 * @createTime 2018-01-02 16:16
 */
@Component
public class AdminResourcesApiHystrix implements AdminResourcesApi {
    @Override
    public Result<MenuGroupDTO> groupGet(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuGroupDTO>> listGroup() {
        return Result.timeout();
    }

    @Override
    public Result<MenuGroupDTO> groupSave(MenuGroupSaveReqDTO menuDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> groupUpdate(MenuGroupUpdateReqDTO menuDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> groupRemove(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<MenuDTO> get(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuTreeDTO>> findTree(String menuGroupCode) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuDTO>> list(String menuGroupCode) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuDTO>> findByParentId(Long parentId) {
        return Result.timeout();
    }

    @Override
    public Result<MenuDTO> save(MenuSaveDTO menuDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> update(MenuUpdateDTO menuDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> remove(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<PageInfo<ResourceDTO>> page(OpenApiReq openApiReq, ResourcePageReqDTO resourcePageReqDTO) {
        return Result.timeout();
    }

    @Override
    public Result<ResourceDTO> resourceGet(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<ResourceDTO> resourceSave(ResourceSaveReqDTO resourceDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> resourceUpdate(ResourceUpdateReqDTO resourceDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> resourceRemove(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuDTO>> listMenu() {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuTreeDTO>> treeMenu() {
        return Result.timeout();
    }

    @Override
    public Result<List<ResourceDTO>> listResource() {
        return Result.timeout();
    }
}
