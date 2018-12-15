package com.github.yupc.auth.controller;


import com.github.yupc.auth.api.AuthTokenApi;
import com.github.yupc.auth.bean.JwtAuthRequest;
import com.github.yupc.auth.common.jwt.TokenVo;
import com.github.yupc.auth.dto.TokenDTO;
import com.github.yupc.auth.service.AuthService;
import com.github.yupc.base.Result;
import com.github.yupc.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 客户端获取token
 * jwt token管理
 *
 * @author yupc
 */
@RestController
@RequestMapping("/client")
@Api(value = "客服端token申请", description = "客服端token申请， 用于各个内部微服务之间的token申请")
public class AuthController implements AuthTokenApi {
    @Value("${jwt.token-header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    /**
     * 获取token
     *
     * @param jwtAuthRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result<TokenVo> token(@RequestBody JwtAuthRequest jwtAuthRequest) throws BizException {
        return Result.success(authService.login(jwtAuthRequest.getUserName(), jwtAuthRequest.getPassWord()));
    }

    /**
     * 获取token
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    @ApiOperation(value = "获取应用token", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<TokenDTO> login(@RequestParam(value = "userName") String userName) throws BizException {
        return Result.success(authService.login(userName));
    }

    /**
     * 刷新
     *
     * @return
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public Result<TokenVo> refreshAndGetAuthenticationToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws BizException {
        TokenVo refreshedToken = authService.refresh(jwtAuthRequest.getUserName(), jwtAuthRequest.getPassWord());
        return Result.success(refreshedToken);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public Result<Boolean> verify(String token) throws BizException {
        authService.validate(token);
        return Result.success(true);
    }

    /**
     * 注销token
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/invalid", method = RequestMethod.POST)
    public Result<Boolean> invalid(String token) throws BizException {
        authService.invalid(token);
        return Result.success(true);
    }
}
