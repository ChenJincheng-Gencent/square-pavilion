package com.square.mall.inventory.center.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 库存中心服务启动类
 *
 * @author Gencent
 * @date 2021/2/20
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class, scanBasePackages = {"com.square.mall"})
@EnableDiscoveryClient
@EnableFeignClients
public class InventoryCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryCenterApplication.class, args);
    }
}
