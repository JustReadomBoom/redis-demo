package com.redis.service.global_exception;

/**
 * @Author: zqz
 * @Description:
 * @Date: Created in 14:18 2021/1/28
 */
public class MyException extends RuntimeException {

    private Integer code;
    private String message;

    public MyException(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public MyException(RespEnum respEnum){
        this.code = respEnum.getCode();
        this.message = respEnum.getMsg();
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
