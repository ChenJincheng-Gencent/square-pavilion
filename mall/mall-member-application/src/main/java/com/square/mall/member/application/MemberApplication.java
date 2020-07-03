package com.square.mall.member.application;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 会员应用服务启动类
 *
 * @author Gencent
 * @date 2019/8/31
 */

@SpringBootApplication
@EnableDubbo
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
