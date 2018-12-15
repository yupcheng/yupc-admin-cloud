package com.github.yupc.commons.handler;


import com.github.yupc.base.Result;
import com.github.yupc.commons.exception.core.ExceptionCode;
import com.github.yupc.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yupc
 * @createTime 2017-12-13 17:04
 */
@ControllerAdvice(value = {
        "com.github.yupc.auth",
        "com.github.yupc.gateway",
        "com.github.yupc.admin.impl",
        "com.github.yupc.open.impl",
})
@ResponseBody  //返回结果为json
public class GlobalExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public Result baseExceptionHandler(BizException ex) {
        log.error("BizException:", ex);
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result otherExceptionHandler(Exception ex) {
        log.error("Exception:", ex);
        return new Result(ExceptionCode.SYSTEM_BUSY.getCode(), null, ex.getMessage());
    }
}