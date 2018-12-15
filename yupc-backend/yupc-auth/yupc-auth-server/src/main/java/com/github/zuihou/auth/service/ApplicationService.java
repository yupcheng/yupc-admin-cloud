package com.github.yupc.auth.service;


import com.github.yupc.auth.common.jwt.TokenVo;

/**
 * @author yupc
 * @createTime 2017-12-13 23:04
 */
public interface ApplicationService {
    TokenVo applyToken(String appId, String appSecret) ;
}
