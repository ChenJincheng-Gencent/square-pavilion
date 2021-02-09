package com.square.mall.job.application.config;

import com.square.mall.job.application.handler.SimpleJobListener;
import org.apache.shardingsphere.elasticjob.infra.listener.ElasticJobListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ScheduledTaskConfig {

    @Bean("simpleJobListener")
    public ElasticJobListener elasticJobListener() {
        return new SimpleJobListener();
    }

}
