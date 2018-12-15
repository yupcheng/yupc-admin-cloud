package com.github.yupc.file.rest.file.api;

import com.github.yupc.base.Result;
import com.github.yupc.file.rest.file.api.hystrix.UploadApiHystrix;
import com.github.yupc.file.rest.file.dto.UploadListDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@FeignClient(name = "${yupc.feign-server.gateway:yupc-gateway-server}", fallback = UploadApiHystrix.class)
public interface UploadApi {

    /**
     * 多文件上传
     * @param request
     * @return
     */
    @RequestMapping(value = "/api/file/upload/multi", method = RequestMethod.POST)
    Result<UploadListDTO> uploadMulti(HttpServletRequest request) throws IOException, ServletException;

}
