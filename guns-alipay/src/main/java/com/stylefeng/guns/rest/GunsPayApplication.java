package com.stylefeng.guns.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.stylefeng.guns"})
public class GunsPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunsPayApplication.class, args);
    }
}
