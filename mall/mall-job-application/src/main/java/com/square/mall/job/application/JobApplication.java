package com.square.mall.job.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 定时任务应用服务启动类
 *
 * @author Gencent
 * @date 2021/2/7
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }
}
