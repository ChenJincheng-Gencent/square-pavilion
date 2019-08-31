package com.square.mall.member.center.service;


import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 会员中心服务启动类
 *
 * @author Gencent
 * @date 2019/8/19
 */

@SpringBootApplication
@EnableDubboConfiguration
public class MemberCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberCenterApplication.class, args);
    }
}
