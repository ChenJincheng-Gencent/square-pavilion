package com.square.mall.member.center.biz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 会员中心服务启动类
 *
 * @author Gencent
 * @date 2019/8/19
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class, scanBasePackages = {"com.square.mall"})
@EnableDiscoveryClient
@EnableFeignClients
public class MemberCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberCenterApplication.class, args);
    }
}
