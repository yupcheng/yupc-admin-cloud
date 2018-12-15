package com.github.yupc.auth.service.impl;


import com.github.yupc.admin.entity.account.domain.AccountDO;
import com.github.yupc.admin.entity.account.po.Admin;
import com.github.yupc.admin.repository.account.service.AdminService;
import com.github.yupc.auth.common.jwt.JWTInfo;
import com.github.yupc.auth.common.jwt.TokenVo;
import com.github.yupc.auth.dto.TokenDTO;
import com.github.yupc.auth.service.AuthService;
import com.github.yupc.auth.util.client.JwtTokenUtil;
import com.github.yupc.commons.exception.core.ExceptionCode;
import com.github.yupc.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yupc
 * @createTime 2017-12-15 13:42
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AdminService adminService;

    @Override
    public TokenVo login(String userName, String passWord) throws BizException {
        if (userName == null || passWord == null
                || userName.isEmpty() || passWord.isEmpty()) {
            throw new BizException(ExceptionCode.USER_NAME_PWD_ERROR.getCode(), ExceptionCode.USER_NAME_PWD_ERROR.getMsg());
        }
        Admin admin = adminService.get(userName, passWord);
        if (admin == null) {
            throw new BizException(ExceptionCode.USER_NAME_PWD_ERROR.getCode(), ExceptionCode.USER_NAME_PWD_ERROR.getMsg());
        }
        return jwtTokenUtil.generateToken(new JWTInfo(admin.getUsername(), admin.getId(), admin.getName(), admin.getAppId()));
    }

    @Override
    public TokenDTO login(String userName) throws BizException {
        if (userName == null || userName.isEmpty()) {
            throw new BizException(ExceptionCode.USER_NAME_PWD_ERROR.getCode(), ExceptionCode.USER_NAME_PWD_ERROR.getMsg());
        }
        AccountDO account = adminService.getAccount(userName);
        if (account == null) {
            throw new BizException(ExceptionCode.USER_NAME_PWD_ERROR.getCode(), ExceptionCode.USER_NAME_PWD_ERROR.getMsg());
        }
        TokenVo tokenVo = jwtTokenUtil.generateToken(new JWTInfo(account.getUserName(), account.getAdminId(), account.getName(), account.getAppId()));
        return new TokenDTO(tokenVo.getToken(), tokenVo.getExpire());
    }

    @Override
    public TokenVo refresh(String userName, String passWord) throws BizException {
        return null;
    }

    @Override
    public void validate(String token) throws BizException {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public void invalid(String token) throws BizException {

    }
}
