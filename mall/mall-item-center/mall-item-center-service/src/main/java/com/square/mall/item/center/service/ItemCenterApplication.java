package com.square.mall.item.center.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 商品中心服务启动类
 *
 * @author Gencent
 * @date 2020/7/22
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class, scanBasePackages = {"com.square.mall"})
@EnableDiscoveryClient
@EnableFeignClients
public class ItemCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemCenterApplication.class, args);
    }
}

