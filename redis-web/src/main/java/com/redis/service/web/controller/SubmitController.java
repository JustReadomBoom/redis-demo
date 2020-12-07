package com.redis.service.web.controller;

import com.alibaba.fastjson.JSON;
import com.redis.service.config.NoRepeatSubmit;
import com.redis.service.demo.ApiResult;
import com.redis.service.demo.UserBean;
import com.redis.service.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zqz
 * @Description: 请求防重测试
 * @Date: Created in 10:25 2020/10/23
 */
@RestController
@RequestMapping("/submit")
public class SubmitController {
    private static final Logger log = LoggerFactory.getLogger(SubmitController.class);
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @PostMapping("test")
    @NoRepeatSubmit
    public ApiResult testSubmit(@RequestBody UserBean userBean) {
        try {
            // 模拟业务场景
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ApiResult(200, "成功", userBean.getUserId());
    }

    @GetMapping("/test2")
    public String test2Submit() {
        String url = "http://localhost:8577/submit/test";
        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> {
                try {
                    log.info("Thread:[{}], time:[{}]", Thread.currentThread().getName(), System.currentTimeMillis());
                    UserBean bean = new UserBean();
                    bean.setUserId(UUID.randomUUID().toString());
                    Map<String, String> header = new HashMap<>();
                    header.put("Content-Type", "application/json");
                    header.put("Authorization", "123456");  //用户唯一标识

                    String resp = HttpUtil.postJson(url, header, JSON.toJSONString(bean));
                    log.info("线程:[{}]-提交请求的响应:[{}]", Thread.currentThread().getName(), resp);
                    ApiResult apiResult = JSON.toJavaObject(JSON.parseObject(resp), ApiResult.class);
                    if ("成功".equals(apiResult.getMessage())) {
                        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>成功处理请求<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return "END";
    }
}
