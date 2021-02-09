package com.square.mall.promotion.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 营销中心服务启动类
 *
 * @author Gencent
 * @date 2021/2/9
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class PromotionCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromotionCenterApplication.class, args);
    }
}