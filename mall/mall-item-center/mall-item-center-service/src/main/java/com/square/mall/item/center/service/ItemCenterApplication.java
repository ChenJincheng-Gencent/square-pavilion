package com.square.mall.item.center.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 商品中心服务启动类
 *
 * @author Gencent
 * @date 2020/7/22
 */

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDubbo
public class ItemCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemCenterApplication.class, args);
    }
}

