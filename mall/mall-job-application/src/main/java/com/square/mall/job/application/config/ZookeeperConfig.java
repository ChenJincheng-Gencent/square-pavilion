package com.square.mall.job.application.config;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * zk配置
 * @author Gencent
 * @date 2021/2/8
 */
@Configuration
@Getter
@Slf4j
public class ZookeeperConfig {

    @Value("${elasticjob.zookeeper.serverLists}")
    private String serverLists;

    @Value("${elasticjob.zookeeper.namespace}")
    private String nameSpace;

    @Bean
    public ZookeeperConfiguration zkConfig() {
        return new ZookeeperConfiguration(serverLists, nameSpace);
    }


    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zkRegistryCenter(ZookeeperConfiguration configuration) {
        return new ZookeeperRegistryCenter(configuration);
    }
}

