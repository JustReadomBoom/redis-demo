package com.redis.service.global_exception;

import java.io.Serializable;

/**
 * @Author: zqz
 * @Description:
 * @Date: Created in 14:07 2021/1/28
 */
public class BaseResp<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;


    public BaseResp(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }


    public BaseResp(T data){
        super();
        this.code = RespEnum.SUCCESS.getCode();
        this.msg = RespEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
