package com.redis.service.web.controller;

import com.alibaba.fastjson.JSON;
import com.redis.service.RedissonService;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zqz
 * @Description:
 * @Date: Created in 11:54 2020/12/2
 */
@RestController
@RequestMapping("/test")
public class RedissonTestController {
    private static Logger log = LoggerFactory.getLogger(RedissonTestController.class);

    @Autowired
    private RedissonService redissonService;


    @GetMapping("/redisson")
    public Object redissonTest(@RequestParam("key") String key) {
            RLock lock = redissonService.getRLock(key);
            log.info("lock={}", lock);
            try {
                boolean bs = lock.tryLock(5, 6, TimeUnit.SECONDS);
                if (bs) {
                    log.info("处理业务。。。。。。");
                    lock.unlock();
                } else {
                    Thread.sleep(300);
                }
            } catch (Exception e) {
                log.error("Test Redisson Error:", e.getMessage(), e);
                lock.unlock();
            }

        return "END";
    }
}
