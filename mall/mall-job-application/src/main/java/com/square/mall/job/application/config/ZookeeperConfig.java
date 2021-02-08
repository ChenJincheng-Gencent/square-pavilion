package com.square.mall.job.application.config;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
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
        ZookeeperConfiguration configuration = new ZookeeperConfiguration(serverLists, nameSpace);
        return configuration;
    }


    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter(ZookeeperConfiguration configuration) {
        log.info("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        return new ZookeeperRegistryCenter(configuration);
    }
}

