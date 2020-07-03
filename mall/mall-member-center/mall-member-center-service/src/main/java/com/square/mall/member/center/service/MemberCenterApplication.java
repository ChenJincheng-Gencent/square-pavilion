package com.square.mall.member.center.service;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 会员中心服务启动类
 *
 * @author Gencent
 * @date 2019/8/19
 */

@SpringBootApplication
@EnableDubbo
public class MemberCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberCenterApplication.class, args);
    }
}