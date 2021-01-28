package com.redis.service.web.controller;

import com.alibaba.fastjson.JSON;
import com.redis.service.global_exception.BaseResp;
import com.redis.service.global_exception.MyException;
import com.redis.service.global_exception.RespEnum;
import com.redis.service.global_exception.TestOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * @Author: zqz
 * @Description:
 * @Date: Created in 14:47 2021/1/28
 */
@RestController
@RequestMapping("/exception")
public class GlobalExceptionController {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionController.class);

    @PostMapping("/order")
    public BaseResp<TestOrder> testException(@Valid @RequestBody TestOrder req) {
        log.info("GlobalExceptionController-testException-req:[{}]", JSON.toJSONString(req));
        TestOrder resp = new TestOrder();
        resp.setOrderNo("RESP0000001");
        resp.setOrderName("TEST RESP NAME");
        resp.setMobileNo("15820759592");
        resp.setOrderAmt(new BigDecimal("100000"));
        return new BaseResp<>(resp);
    }
}
