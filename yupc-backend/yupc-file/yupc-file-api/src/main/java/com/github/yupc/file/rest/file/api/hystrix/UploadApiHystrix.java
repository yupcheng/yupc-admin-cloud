package com.github.yupc.file.rest.file.api.hystrix;

import com.github.yupc.base.Result;
import com.github.yupc.file.rest.file.api.UploadApi;
import com.github.yupc.file.rest.file.dto.UploadListDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UploadApiHystrix implements UploadApi {
    @Override
    public Result<UploadListDTO> uploadMulti(HttpServletRequest request) {
        return Result.timeout();
    }
}
