package com.github.yupc.auth.api;

import com.github.yupc.auth.dto.TokenDTO;
import com.github.yupc.base.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${yupc.feign-server.auth:yupc-auth-server}")
public interface AuthTokenApi {

    /**
     * 登录获取token
     * @param userName
     * @return
     */
    @RequestMapping(value = "/client/login", method = RequestMethod.POST)
    Result<TokenDTO> login(@RequestParam(value = "userName") String userName);
}
