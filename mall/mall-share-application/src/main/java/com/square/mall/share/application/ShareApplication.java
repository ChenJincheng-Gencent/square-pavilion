package com.square.mall.share.application;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 商品应用服务启动类
 *
 * @author Gencent
 * @date 2020/7/24
 */

@SpringBootApplication
@EnableDubbo
public class ShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareApplication.class, args);
    }
}
