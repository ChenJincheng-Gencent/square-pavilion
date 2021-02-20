package com.square.mall.manager.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 管理应用服务启动类
 *
 * @author Gencent
 * @date 2020/7/24
 */
@SpringBootApplication(scanBasePackages = {"com.square.mall"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.square.mall"})
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}