package com.stylefeng.guns.rest;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.stylefeng.guns"})
@EnableDubboConfiguration
public class GunsCinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunsCinemaApplication.class, args);
    }
}
