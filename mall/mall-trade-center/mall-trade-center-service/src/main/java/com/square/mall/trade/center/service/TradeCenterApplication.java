package com.square.mall.trade.center.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 交易中心服务启动类
 *
 * @author Gencent
 * @date 2020/7/22
 */

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class TradeCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeCenterApplication.class, args);
    }
}


