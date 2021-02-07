package com.square.mall.job.application.config;

import lombok.Data;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 定时任务配置
 *
 * @author Gencent
 * @date 2021/2/7
 */
@Configuration
@Data
public class JobConfig {

    @Value("${elasticjob.regCenter.serverLists}")
    private String serverLists;

    @Value("${elasticjob.regCenter.namespace}")
    private String namespace;

    @Resource
    private DataSource jobDataSource;

    @Bean
    public ZookeeperConfiguration zkConfig() {
        return new ZookeeperConfiguration(serverLists, namespace);
    }

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(ZookeeperConfiguration config) {
        return new ZookeeperRegistryCenter(config);
    }

}
