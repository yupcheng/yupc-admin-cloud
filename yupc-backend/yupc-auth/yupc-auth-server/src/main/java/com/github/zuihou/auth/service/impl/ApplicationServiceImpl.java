package com.github.yupc.auth.service.impl;

import com.github.yupc.admin.entity.account.po.Applications;
import com.github.yupc.admin.repository.account.service.ApplicationsService;
import com.github.yupc.auth.common.jwt.JWTInfo;
import com.github.yupc.auth.common.jwt.TokenVo;
import com.github.yupc.auth.service.ApplicationService;
import com.github.yupc.auth.util.app.AppTokenUtil;
import com.github.yupc.commons.exception.core.ExceptionCode;
import com.github.yupc.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yupc
 * @createTime 2017-12-13 23:30
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private AppTokenUtil appTokenUtil;
    @Autowired
    private ApplicationsService applicationsService;

    @Override
    public TokenVo applyToken(String appId, String appSecret) throws BizException {
        Applications client = getClient(appId, appSecret);
        return appTokenUtil.generateToken(new JWTInfo("", null, client.getAppName(), client.getAppId()));
    }

    private Applications getClient(String appId, String appSecret) throws BizException {
        if (appId == null || appSecret == null
                || appId.isEmpty() || appSecret.isEmpty()) {
            throw new BizException(ExceptionCode.JWT_APPID_SECRET_INVALID.getCode(),
                    ExceptionCode.JWT_APPID_SECRET_INVALID.getMsg());
        }
        Applications app = applicationsService.getBySecret(appId, appSecret);
        if (app == null) {
            throw new BizException(ExceptionCode.JWT_APPID_SECRET_INVALID.getCode(),
                    ExceptionCode.JWT_APPID_SECRET_INVALID.getMsg());
        }
        if (!app.getIsEnable()) {
            throw new BizException(ExceptionCode.JWT_APPID_ENABLED.getCode(),
                    ExceptionCode.JWT_APPID_ENABLED.getMsg());
        }
        return app;
    }

    //TODO 这里差一个registryClient方法: 定时查询注册到eureka中的服务，查询他们的可用client，并用bus推送到各个服务
}
