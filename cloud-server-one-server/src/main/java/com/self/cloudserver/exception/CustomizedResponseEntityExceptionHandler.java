package com.self.cloudserver.exception;

import com.self.cloudserver.constants.Response;
import com.self.cloudserver.enums.RespCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final Response<Object> handleAllExceptions(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return Response.addError(RespCodeEnum.FAIL.getCode(), ex.getMessage());
    }

}
