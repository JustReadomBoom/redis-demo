package com.redis.service.global_exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @Author: zqz
 * @Description:
 * @Date: Created in 14:19 2021/1/28
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MyException.class)
    public BaseResp myExceptionHandler(MyException e) {
        return new BaseResp(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    public BaseResp throwableHandler(Throwable t) {
        return new BaseResp(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                StringUtils.isBlank(t.getMessage())
                        ? HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                        : t.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResp paramHandler(MethodArgumentNotValidException e) {
        return new BaseResp(RespEnum.ILLEGAL_ARGUMENT.getCode(), Optional.ofNullable(e)
                .map(MethodArgumentNotValidException::getBindingResult)
                .map(Errors::getFieldError)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(RespEnum.ILLEGAL_ARGUMENT.getMsg()));
    }
}
