package com.github.yupc.auth.util.client;


import com.github.yupc.auth.common.jwt.IJWTInfo;
import com.github.yupc.auth.common.jwt.JWTHelper;
import com.github.yupc.auth.common.jwt.TokenVo;
import com.github.yupc.exception.BizException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by yupc on 2017/9/10.
 */
@Component
public class JwtTokenUtil {

    @Value("${client.expire}")
    private int expire;
    @Value("${client.pri-key.path}")
    private String priKeyPath;
    @Value("${client.pub-key.path}")
    private String pubKeyPath;

    public TokenVo generateToken(IJWTInfo jwtInfo) throws BizException {
        return JWTHelper.generateToken(jwtInfo, priKeyPath, expire);
    }

    public IJWTInfo getInfoFromToken(String token) throws BizException {
        return JWTHelper.getInfoFromToken(token, pubKeyPath);
    }


}
