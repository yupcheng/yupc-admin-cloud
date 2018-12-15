package com.github.yupc.admin.rest.authority;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yupc.admin.constant.ResourcesType;
import com.github.yupc.admin.entity.authority.po.AdminMenuGroup;
import com.github.yupc.admin.entity.authority.po.AdminResources;
import com.github.yupc.admin.repository.authority.example.AdminMenuGroupExample;
import com.github.yupc.admin.repository.authority.example.AdminResourcesExample;
import com.github.yupc.admin.repository.authority.service.AdminMenuGroupService;
import com.github.yupc.admin.repository.authority.service.AdminResourcesService;
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
import com.github.yupc.admin.rest.dozer.DozerUtils;
import com.github.yupc.admin.utils.TreeUtil;
import com.github.yupc.base.Result;
import com.github.yupc.commons.constant.DeleteStatus;
import com.github.yupc.commons.constant.EnableStatus;
import com.github.yupc.commons.context.BaseContextHandler;
import com.github.yupc.commons.context.CommonConstants;
import com.github.yupc.commons.exception.core.ExceptionCode;
import com.github.yupc.page.plugins.openapi.OpenApiReq;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static com.github.yupc.utils.BizAssert.assertNotEmpty;
import static com.github.yupc.utils.BizAssert.assertNotNull;

/**
 * @author yupc
 * @createTime 2017-12-15 11:17
 */
@Api(value = "API - AdminResourcesApiImpl", description = "菜单/资源管理")
@RestController
@RequestMapping("menu")
@Slf4j
public class AdminResourcesApiImpl implements AdminResourcesApi {
    /**
     * 最大菜单组数量
     */
    private final static int MENU_GROUP_MAX = 20;
    /**
     * 单个菜单组中最大菜单数量
     */
    private final static int MENU_MAX = 500;
    @Autowired
    private AdminResourcesService adminResourcesService;
    @Autowired
    private AdminMenuGroupService adminMenuGroupService;
    @Autowired
    private DozerUtils dozerUtils;

    @Override
    @ApiOperation(value = "获取单个菜单组", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public Result<MenuGroupDTO> groupGet(@PathVariable(value = "id")  Long id) {
        String appId = BaseContextHandler.getAppId();
        AdminMenuGroup menuGroup = adminMenuGroupService.getByAppIdAndId(appId, id);
        if (menuGroup.getIsDelete()) {
            return Result.success(null);
        }
        return Result.success(dozerUtils.map(menuGroup, MenuGroupDTO.class));
    }

    @Override
    @ApiOperation(value = "获取所有菜单组", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public Result<List<MenuGroupDTO>> listGroup() {
        String appId = BaseContextHandler.getAppId();
        AdminMenuGroupExample example = new AdminMenuGroupExample();
        example.createCriteria().andAppIdEqualTo(appId).andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        List<AdminMenuGroup> menuGroupList = adminMenuGroupService.find(example);
        return Result.success(dozerUtils.mapList(menuGroupList, MenuGroupDTO.class));
    }


    @Override
    @ApiOperation(value = "新增菜单组", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值 菜单组最多20个")
    @ApiResponses({
            @ApiResponse(code = 51000, message = "菜单组不能为空"),
            @ApiResponse(code = 51001, message = "菜单组编码不能为空"),
            @ApiResponse(code = 51002, message = "菜单组CODE已存在"),
            @ApiResponse(code = 51003, message = "菜单组最多只能创建20个"),
    })
    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public Result<MenuGroupDTO> groupSave(@RequestBody MenuGroupSaveReqDTO menuGroupDto) {
        assertNotNull(ExceptionCode.MENU_GROUP_NULL, menuGroupDto);
        assertNotEmpty(ExceptionCode.MENU_GROUP_CODE_EMPTY, menuGroupDto.getCode());

        String appId = BaseContextHandler.getAppId();
        if (adminMenuGroupService.check(appId, menuGroupDto.getCode())) {
            return Result.fail(ExceptionCode.MENU_GROUP_EXIST);
        }
        AdminMenuGroupExample example = new AdminMenuGroupExample();
        example.createCriteria().andAppIdEqualTo(appId).andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        int count = adminMenuGroupService.count(example);
        if (count >= MENU_GROUP_MAX) {
            return Result.fail(ExceptionCode.MENU_GROUP_TOO_MUCH);
        }

        AdminMenuGroup menuGroup = dozerUtils.map(menuGroupDto, AdminMenuGroup.class);
        menuGroup.setCreateUser(BaseContextHandler.getUserName());
        menuGroup.setUpdateUser(BaseContextHandler.getUserName());
        menuGroup.setIsDelete(DeleteStatus.UN_DELETE.getVal());
        menuGroup.setAppId(appId);
        menuGroup = adminMenuGroupService.saveSelective(menuGroup);
        return Result.success(dozerUtils.map(menuGroup, MenuGroupDTO.class));
    }

    @Override
    @ApiOperation(value = "修改菜单组", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @ApiResponses({
            @ApiResponse(code = 51000, message = "菜单组不能为空"),
            @ApiResponse(code = 51004, message = "菜单组id不能为空"),
    })
    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public Result<Boolean> groupUpdate(@RequestBody MenuGroupUpdateReqDTO menuGroupUpdateReqDto) {
        assertNotNull(ExceptionCode.MENU_GROUP_NULL, menuGroupUpdateReqDto);
        assertNotNull(ExceptionCode.MENU_GROUP_ID_NULL, menuGroupUpdateReqDto.getId());

        String appId = BaseContextHandler.getAppId();
        AdminMenuGroup menuGroup = dozerUtils.map(menuGroupUpdateReqDto, AdminMenuGroup.class);
        menuGroup.setUpdateUser(BaseContextHandler.getUserName());
        adminMenuGroupService.updateByAppIdAndIdSelective(appId, menuGroup);
        return Result.success(true);
    }

    @Override
    @ApiOperation(value = "删除菜单组", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值 <br/> 为了数据安全存在子菜单的菜单组不能被删除")
    @ApiResponses({
            @ApiResponse(code = 51005, message = "该菜单组存在子菜，无法删除"),
    })
    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public Result<Boolean> groupRemove(@PathVariable(value = "id")  Long id) {
        String appId = BaseContextHandler.getAppId();
        //1，检测是否存在子菜单
        if (adminResourcesService.checkMenu(appId, id)) {
            return Result.fail(ExceptionCode.MENU_GROUP_EXIST_CHILD.getCode(),
                    ExceptionCode.MENU_GROUP_EXIST_CHILD.getMsg());
        }
        adminResourcesService.removeByAppIdAndId(appId, id);
        return Result.success(true);
    }

    @Override
    @ApiOperation(value = "获取单个菜单", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<MenuDTO> get(@PathVariable(value = "id")  Long id) {
        String appId = BaseContextHandler.getAppId();
        AdminResources resources = adminResourcesService.getByAppIdAndId(appId, id);
        if (resources == null || resources.getIsDelete()) {
            return Result.success(null);
        }
        return Result.success(dozerUtils.map(resources, MenuDTO.class));
    }

    @Override
    @ApiOperation(value = "获取菜单树", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @ApiImplicitParam(name = "menuGroupCode", value = "菜单组编码", defaultValue = "DEF", dataType = "string", paramType = "query")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public Result<List<MenuTreeDTO>> findTree(@RequestParam(value = "menuGroupCode", required = false, defaultValue = "DEF") String menuGroupCode) {
        String appId = BaseContextHandler.getAppId();
        if (Strings.isNullOrEmpty(menuGroupCode)) {
            menuGroupCode = CommonConstants.MENU_GROUP_CODE_DEF;
        }
        AdminResourcesExample example = new AdminResourcesExample();
        example.createCriteria().andAppIdEqualTo(appId).andMenuGroupCodeEqualTo(menuGroupCode)
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal())
                .andTypeIn(Arrays.asList(ResourcesType.DIR.toString(), ResourcesType.MENU.toString()));
        List<AdminResources> resourcesList = adminResourcesService.find(example);
        List<MenuTreeDTO> treeList = dozerUtils.mapList(resourcesList, MenuTreeDTO.class);
        return Result.success(TreeUtil.bulid(treeList, CommonConstants.PARENT_ID_DEF));
    }

    @Override
    @ApiOperation(value = "获取所有菜单", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @ApiImplicitParam(name = "menuGroupCode", value = "菜单组编码", defaultValue = "DEF", dataType = "string", paramType = "query")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result<List<MenuDTO>> list(@RequestParam(value = "menuGroupCode", required = false, defaultValue = "DEF") String menuGroupCode) {
        String appId = BaseContextHandler.getAppId();
        if (Strings.isNullOrEmpty(menuGroupCode)) {
            menuGroupCode = CommonConstants.MENU_GROUP_CODE_DEF;
        }
        AdminResourcesExample example = new AdminResourcesExample();
        example.createCriteria().andAppIdEqualTo(appId).andMenuGroupCodeEqualTo(menuGroupCode)
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal())
                .andTypeIn(Arrays.asList(ResourcesType.DIR.toString(), ResourcesType.MENU.toString()));
        List<AdminResources> resourcesList = adminResourcesService.find(example);
        return Result.success(dozerUtils.mapList(resourcesList, MenuDTO.class));
    }

    @Override
    @ApiOperation(value = "获取所有的子菜单", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/{parentId}/children", method = RequestMethod.GET)
    public Result<List<MenuDTO>> findByParentId(@PathVariable(value = "parentId") Long parentId) {
        String appId = BaseContextHandler.getAppId();
        AdminResourcesExample example = new AdminResourcesExample();
        example.createCriteria().andAppIdEqualTo(appId).andParentIdEqualTo(parentId)
                .andTypeIn(Arrays.asList(ResourcesType.DIR.toString(), ResourcesType.MENU.toString()))
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        List<AdminResources> resourcesList = adminResourcesService.find(example);
        return Result.success(dozerUtils.mapList(resourcesList, MenuDTO.class));
    }

    @Override
    @ApiOperation(value = "新增菜单", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值<br/>单个菜单组下的菜单最多只能有500个")
    @ApiResponses({
            @ApiResponse(code = 51100, message = "菜单不能为空"),
            @ApiResponse(code = 51101, message = "菜单编码[code]不能为空"),
            @ApiResponse(code = 51102, message = "菜单类型[type]不能为空"),
            @ApiResponse(code = 51103, message = "菜单组编码[code]不存在"),
            @ApiResponse(code = 51104, message = "每组菜单最多只能创建500个"),
            @ApiResponse(code = 51201, message = "菜单/资源编码[code]已存在"),
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<MenuDTO> save(@RequestBody MenuSaveDTO menuDto) {
        //1，验证参数
        assertNotNull(ExceptionCode.MENU_NULL, menuDto);
        assertNotEmpty(ExceptionCode.MENU_CODE_EMPTY, menuDto.getCode());
        assertNotEmpty(ExceptionCode.MENU_TYPE_EMPTY, menuDto.getType());

        String appId = BaseContextHandler.getAppId();
        //2, 验证code是否重复
        if (adminResourcesService.check(appId, menuDto.getCode())) {
            return Result.fail(ExceptionCode.RESOURCES_EXIST);
        }
        //3, 验证menu_group_code是否正确
        if (!Strings.isNullOrEmpty(menuDto.getMenuGroupCode()) && !CommonConstants.MENU_GROUP_CODE_DEF.equals(menuDto.getMenuGroupCode())) {
            if (!adminMenuGroupService.check(appId, menuDto.getMenuGroupCode())) {
                return Result.fail(ExceptionCode.MENU_GROUP_NOT_EXIST);
            }
        } else {
            menuDto.setMenuGroupCode(CommonConstants.MENU_GROUP_CODE_DEF);
        }

        AdminResourcesExample example = new AdminResourcesExample();
        example.createCriteria().andAppIdEqualTo(appId).andTypeIn(Arrays.asList(ResourcesType.DIR.toString(), ResourcesType.MENU.toString()))
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal()).andIsEnableEqualTo(EnableStatus.ENABLE.getVal())
                .andMenuGroupCodeEqualTo(menuDto.getMenuGroupCode());
        int count = adminResourcesService.count(example);
        if (count >= MENU_MAX) {
            return Result.fail(ExceptionCode.MENU_TOO_MUCH);
        }

        AdminResources menu = dozerUtils.map(menuDto, AdminResources.class);
        //4, 设置path
        menu.setPath(CommonConstants.ROOT_PATH_DEF);
        if (menuDto.getParentId() != null && menuDto.getParentId() > 0) {
            AdminResources parent = adminResourcesService.getByAppIdAndId(appId, menuDto.getParentId());
            if (!parent.getIsDelete() && parent.getIsEnable()) {
                menu.setPath(parent.getPath() + parent.getCode() + CommonConstants.ROOT_PATH_DEF);
            }
        }

        //5, 保存
        menu.setAppId(appId);
        menu.setCreateUser(BaseContextHandler.getUserName());
        menu.setUpdateUser(BaseContextHandler.getUserName());
        if (menu.getIsEnable() == null) {
            menu.setIsEnable(EnableStatus.ENABLE.getVal());
        }
        menu.setIsDelete(DeleteStatus.UN_DELETE.getVal());
        menu = adminResourcesService.save(menu);
        return Result.success(dozerUtils.map(menu, MenuDTO.class));
    }

    @Override
    @ApiOperation(value = "修改菜单", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @ApiResponses({
            @ApiResponse(code = 51100, message = "菜单不能为空"),
            @ApiResponse(code = 51105, message = "菜单[id]不能为空"),
    })
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result<Boolean> update(@RequestBody MenuUpdateDTO menuDto) {
        assertNotNull(ExceptionCode.MENU_NULL, menuDto);
        assertNotNull(ExceptionCode.MENU_ID_NULL, menuDto.getId());

        String appId = BaseContextHandler.getAppId();
        AdminResources menu = dozerUtils.map(menuDto, AdminResources.class);
        menu.setUpdateUser(BaseContextHandler.getUserName());
        adminResourcesService.updateByAppIdAndIdSelective(appId, menu);
        return Result.success(true);
    }

    @Override
    @ApiOperation(value = "删除菜单", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @ApiResponses({
            @ApiResponse(code = 51106, message = "该菜单存在子菜单或子资源，无法删除"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result<Boolean> remove(@PathVariable(value = "id") Long id) {
        String appId = BaseContextHandler.getAppId();
        //1，检测是否存在子菜单，子资源
        if (adminResourcesService.checkChildren(appId, id)) {
            return Result.fail(ExceptionCode.MENU_EXIST_CHILD);
        }
        adminResourcesService.removeByAppIdAndId(appId, id);
        return Result.success(true);
    }

    @Override
    @ApiOperation(value = "获取菜单资源分页list", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "第几页", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", defaultValue = "10", dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "/resource/page", method = RequestMethod.GET)
    public Result<PageInfo<ResourceDTO>> page(OpenApiReq openApiReq, ResourcePageReqDTO resourcePageReqDTO) {
        String appId = BaseContextHandler.getAppId();
        AdminResourcesExample example = new AdminResourcesExample();
        example.createCriteria().andAppIdEqualTo(appId).andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal())
                .andParentIdEqualTo(resourcePageReqDTO.getMenuId()).andCodeEqualTo(resourcePageReqDTO.getCode())
                .andTypeIn(Lists.newArrayList(ResourcesType.BUTTON.toString(), ResourcesType.URI.toString()))
                .andNameLike(AdminResourcesExample.fullLike(resourcePageReqDTO.getName()));
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        List<AdminResources> resourcesList = adminResourcesService.find(example);
        List<ResourceDTO> resourceDtoList = dozerUtils.mapPage(resourcesList, ResourceDTO.class);
        return Result.success(new PageInfo<>(resourceDtoList));
    }

    @Override
    @ApiOperation(value = "获取单个菜单下的资源", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
    public Result<ResourceDTO> resourceGet(@PathVariable(value = "id") Long id) {
        String appId = BaseContextHandler.getAppId();
        AdminResources resources = adminResourcesService.getByAppIdAndId(appId, id);
        if (resources.getIsDelete()) {
            return Result.success(null);
        }
        return Result.success(dozerUtils.map(resources, ResourceDTO.class));
    }

    @Override
    @ApiOperation(value = "保存资源", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @ApiResponses({
            @ApiResponse(code = 51200, message = "资源信息不能为空"),
            @ApiResponse(code = 51201, message = "资源编码[code]不能为空"),
            @ApiResponse(code = 51202, message = "资源菜单id[menuId]不能为空"),
            @ApiResponse(code = 51203, message = "资源类型[type]不能为空"),
            @ApiResponse(code = 51204, message = "菜单/资源编码[code]已存在"),
            @ApiResponse(code = 51206, message = "菜单不存在"),
    })
    @RequestMapping(value = "/resource", method = RequestMethod.POST)
    public Result<ResourceDTO> resourceSave(@RequestBody ResourceSaveReqDTO resourceDto) {
        assertNotNull(ExceptionCode.RESOURCES_NULL, resourceDto);
        assertNotEmpty(ExceptionCode.RESOURCES_CODE_EMPTY, resourceDto.getCode());
        assertNotNull(ExceptionCode.RESOURCES_MENU_ID_NULL, resourceDto.getMenuId());
        assertNotEmpty(ExceptionCode.RESOURCES_TYPE_NULL, resourceDto.getType());

        String appId = BaseContextHandler.getAppId();
        //2, 验证code是否重复
        if (adminResourcesService.check(appId, resourceDto.getCode())) {
            return Result.fail(ExceptionCode.RESOURCES_EXIST);
        }
        // 验证资源所属菜单
        AdminResources menu = adminResourcesService.getByAppIdAndId(appId, resourceDto.getMenuId());
        assertNotNull(ExceptionCode.MENU_NOT_EXIST, menu);

        AdminResources resources = dozerUtils.map(resourceDto, AdminResources.class);
        resources.setAppId(appId);
        resources.setMenuGroupCode(menu.getMenuGroupCode());
        resources.setCreateUser(BaseContextHandler.getUserName());
        resources.setUpdateUser(BaseContextHandler.getUserName());
        if (resources.getIsEnable() == null) {
            resources.setIsEnable(EnableStatus.ENABLE.getVal());
        }
        resources.setIsDelete(DeleteStatus.UN_DELETE.getVal());
        resources = adminResourcesService.saveSelective(resources);
        return Result.success(dozerUtils.map(resources, ResourceDTO.class));
    }

    @Override
    @ApiOperation(value = "修改资源", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @ApiResponses({
            @ApiResponse(code = 51200, message = "资源信息不能为空"),
            @ApiResponse(code = 51205, message = "资源id[id]不能为空"),
    })
    @RequestMapping(value = "/resource", method = RequestMethod.PUT)
    public Result<Boolean> resourceUpdate(@RequestBody ResourceUpdateReqDTO resourceDto) {
        assertNotNull(ExceptionCode.RESOURCES_NULL, resourceDto);
        assertNotNull(ExceptionCode.RESOURCES_ID_NULL, resourceDto.getId());

        String appId = BaseContextHandler.getAppId();
        AdminResources resources = dozerUtils.map(resourceDto, AdminResources.class);
        resources.setUpdateUser(BaseContextHandler.getUserName());
        adminResourcesService.updateByAppIdAndIdSelective(appId, resources);
        return Result.success(true);
    }


    /**
     * @param id
     * @return
     * @see com.github.yupc.commons.exception.core.ExceptionCode#DB_REMOVE_ERROR
     */
    @Override
    @ApiOperation(value = "删除资源", notes = "删除指定id的资源")
    @ApiResponses(
            @ApiResponse(code = 10000, message = "无法软删除")
    )
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.DELETE)
    public Result<Boolean> resourceRemove(@PathVariable(value = "id") Long id) {
        String appId = BaseContextHandler.getAppId();
        adminResourcesService.removeByAppIdAndId(appId, id);
        return Result.success(true);
    }


    /**
     * 查询指定userId的菜单列表
     *
     * @return
     */
    @Override
    @ApiOperation(value = "查询当前应用拥有的的菜单列表", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/self", method = RequestMethod.GET)
    public Result<List<MenuDTO>> listMenu() {
        String appId = BaseContextHandler.getAppId();
        List<AdminResources> list = adminResourcesService.findMenu(appId);
        return Result.success(dozerUtils.mapList(list, MenuDTO.class));
    }

    /**
     * 查询指定userId的菜单列表
     *
     * @return
     */
    @Override
    @ApiOperation(value = "查询当前应用拥有的的菜单列表树", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/self/tree", method = RequestMethod.GET)
    public Result<List<MenuTreeDTO>> treeMenu() {
        String appId = BaseContextHandler.getAppId();
        List<AdminResources> menuList = adminResourcesService.findMenu(appId);

        List<MenuTreeDTO> treeList = dozerUtils.mapList(menuList, MenuTreeDTO.class);
        return Result.success(TreeUtil.bulid(treeList, CommonConstants.PARENT_ID_DEF));
    }

    /**
     * 查询指定userId的资源列表
     *
     * @return
     */
    @Override
    @ApiOperation(value = "查询当前应用拥有的的资源列表树", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/resource/self", method = RequestMethod.GET)
    public Result<List<ResourceDTO>> listResource() {
        String appId = BaseContextHandler.getAppId();
        List<AdminResources> list = adminResourcesService.findResources(appId);
        return Result.success(dozerUtils.mapList(list, ResourceDTO.class));
    }
}
