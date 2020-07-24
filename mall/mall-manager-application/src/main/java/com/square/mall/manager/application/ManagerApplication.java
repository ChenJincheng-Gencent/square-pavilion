package com.square.mall.manager.application;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 管理应用服务启动类
 *
 * @author Gencent
 * @date 2020/7/24
 */

@SpringBootApplication
@EnableDubbo
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}