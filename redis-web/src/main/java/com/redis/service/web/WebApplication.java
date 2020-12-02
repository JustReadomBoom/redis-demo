package com.redis.service.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: zqz
 * @Description:
 * @Date: Created in 11:18 2020/12/2
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.redis.service"})
public class WebApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
