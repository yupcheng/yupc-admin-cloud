package com.github.yupc.auth.client.jwt;


import com.github.yupc.auth.client.config.AppAuthConfig;
import com.github.yupc.auth.common.jwt.IJWTInfo;
import com.github.yupc.auth.common.jwt.JWTHelper;
import com.github.yupc.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 第三方
 * Created by yupc on 2017/9/15.
 */
@Configuration
public class AppAuthUtil {
    @Autowired
    private AppAuthConfig appAuthConfig;

    /**
     * 根据token， 公钥 解析 JWT 信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    public IJWTInfo getInfoFromToken(String token) throws BizException {
        return JWTHelper.getInfoFromToken(token, appAuthConfig.getPubKeyPath());
    }
}
