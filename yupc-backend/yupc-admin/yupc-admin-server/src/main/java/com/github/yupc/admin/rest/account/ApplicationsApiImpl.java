package com.github.yupc.admin.rest.account;

import com.github.yupc.admin.repository.account.service.ApplicationsService;
import com.github.yupc.admin.rest.account.api.ApplicationsApi;
import com.github.yupc.admin.rest.account.dto.ApplicationsDTO;
import com.github.yupc.admin.rest.dozer.DozerUtils;
import com.github.yupc.base.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yupc
 * @createTime 2017-12-15 11:06
 */
@Api(value = "API - ApplicationsApiImpl", description = "第三方应用管理")
@Slf4j
@RestController
@RequestMapping("/app")
public class ApplicationsApiImpl implements ApplicationsApi {
    @Autowired
    private ApplicationsService applicationsService;
    @Autowired
    private DozerUtils dozerUtils;

    /**
     * 根据appid 和 密码 查找应用
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<ApplicationsDTO> getBySecret(@RequestParam(value = "appId") String appId, @RequestParam(value = "appSecret") String appSecret) {
        return Result.success(dozerUtils.map(applicationsService.getBySecret(appId, appSecret), ApplicationsDTO.class));
    }
}
