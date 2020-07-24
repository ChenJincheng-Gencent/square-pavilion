package com.square.mall.trade.application;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 交易应用服务启动类
 *
 * @author Gencent
 * @date 2020/7/24
 */

@SpringBootApplication
@EnableDubbo
public class TradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}
