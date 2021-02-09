package com.square.mall.job.application.handler;

import com.square.mall.job.application.annotation.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ElasticJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.error.handler.email.EmailPropertiesConstants;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Map;

/**
 * 定时任务处理器
 */
@Configuration
@Slf4j
@Order(1)
public class ScheduledTaskProcessor implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private ZookeeperRegistryCenter zkRegistryCenter;


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigurableApplicationContext ctx = event.getApplicationContext();
        Map<String, Object> beanMap = ctx.getBeansWithAnnotation(ScheduledTask.class);
        try {

            for (Object confBean : beanMap.values()) {
                if (confBean instanceof ElasticJob) {
                    new ScheduleJobBootstrap(zkRegistryCenter, (ElasticJob)confBean,
                        createJobConfiguration((Class<ElasticJob>)(confBean.getClass()))).schedule();
                }
            }
        } catch (Exception e) {
            log.error("Exception: ", e);
        }

    }

    private JobConfiguration createJobConfiguration(Class<ElasticJob> taskClass) {

        ScheduledTask conf = taskClass.getAnnotation(ScheduledTask.class);
        String taskName = conf.name();
        log.info("taskName: {}", taskName);
        String cron = conf.cron();
        log.info("cron: {}", cron);
        int shardingTotalCount = conf.shardingTotalCount();

        JobConfiguration jobConfig = JobConfiguration.newBuilder(taskName, shardingTotalCount).cron(cron)
            .jobErrorHandlerType("EMAIL").build();
        //setEmailProperties(jobConfig);
        return jobConfig;
    }



}
