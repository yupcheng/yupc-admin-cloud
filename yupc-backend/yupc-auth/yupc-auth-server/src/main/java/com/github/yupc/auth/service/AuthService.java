package com.github.yupc.auth.service;


import com.github.yupc.auth.common.jwt.TokenVo;
import com.github.yupc.auth.dto.TokenDTO;
import com.github.yupc.exception.BizException;

/**
 * @author yupc
 * @createTime 2017-12-15 13:41
 */
public interface AuthService {
    TokenVo login(String userName, String passWord) ;

    TokenDTO login(String userName) throws BizException;

    TokenVo refresh(String userName, String passWord) ;

    void validate(String token);

    void invalid(String token) ;
}
