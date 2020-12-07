package com.redis.service.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.redis.service.RedissonService;
import com.redis.service.utils.HttpUtil;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zqz
 * @Description: redisson分布式锁(未验证)
 * @Date: Created in 11:54 2020/12/2
 */
@RestController
@RequestMapping("/test")
public class RedissonTestController {
    private static Logger log = LoggerFactory.getLogger(RedissonTestController.class);
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private RedissonService redissonService;



    @PostMapping("/start/demo")
    public Object startDemo(){
        String key = UUID.randomUUID().toString();
        RLock lock = redissonService.getRLock(key);
        try{
            log.info("==================");
            boolean bs = lock.tryLock(5, 6, TimeUnit.SECONDS);
            if (bs) {
                log.info("处理业务。。。。。。");
                lock.unlock();
            } else {
                Thread.sleep(300);
            }
        }catch (Exception e){
            log.error("ERRROR:[{}]", e.getMessage(), e);
            lock.unlock();
        }
        return "DEMO END";
    }




    @GetMapping("/redisson")
    public Object redissonTest() {
        String url="http://localhost:8577/test/start/demo";
        for(int i=0; i<200; i++){
            executorService.submit(() -> {
                try {
                    System.out.println("Thread:"+Thread.currentThread().getName()+", time:"+System.currentTimeMillis());
                    JSONObject obj = new JSONObject();
                    obj.put("name", "zhouqizhi");
                    String resp = HttpUtil.postJson(url, null, obj.toJSONString());
                    log.info("线程:[{}]-测试响应结果:[{}]", Thread.currentThread().getName(), resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return "END";
    }
}
