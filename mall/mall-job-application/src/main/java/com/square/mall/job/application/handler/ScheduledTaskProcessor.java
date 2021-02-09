package com.square.mall.job.application.handler;

import com.square.mall.job.application.annotation.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ElasticJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.error.handler.email.EmailPropertiesConstants;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 定时任务处理器
 *
 * @author Gencent
 * @date 2021/2/9
 */
@Configuration
@Slf4j
@Order(1)
public class ScheduledTaskProcessor implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private ZookeeperRegistryCenter zkRegistryCenter;

    @Autowired
    private DataSource jobDataSource;


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

    /**
     * 创建任务配置
     *
     * @param taskClass 任务class
     * @return 任务配置
     */
    private JobConfiguration createJobConfiguration(Class<ElasticJob> taskClass) {

        ScheduledTask conf = taskClass.getAnnotation(ScheduledTask.class);
        String taskName = conf.name();
        log.info("taskName: {}", taskName);
        String cron = conf.cron();
        log.info("cron: {}", cron);
        int shardingTotalCount = conf.shardingTotalCount();

        // 定义日志数据库事件溯源配置
        TracingConfiguration<DataSource> tracingConfig = new TracingConfiguration<>("RDB", jobDataSource);

        JobConfiguration jobConfig = JobConfiguration.newBuilder(taskName, shardingTotalCount).cron(cron)
            .jobErrorHandlerType("EMAIL").jobListenerTypes("simpleJobListener").build();
        jobConfig.getExtraConfigurations().add(tracingConfig);
        //设置邮件的配置
        setEmailProperties(jobConfig);
        return jobConfig;
    }

    /**
     * 设置邮件的配置
     *
     * @param jobConfig 任务配置
     */
    private static void setEmailProperties(final JobConfiguration jobConfig) {

        jobConfig.getProps().setProperty(EmailPropertiesConstants.HOST, "host");
        jobConfig.getProps().setProperty(EmailPropertiesConstants.PORT, "465");
        jobConfig.getProps().setProperty(EmailPropertiesConstants.USERNAME, "username");
        jobConfig.getProps().setProperty(EmailPropertiesConstants.PASSWORD, "password");
        jobConfig.getProps().setProperty(EmailPropertiesConstants.FROM, "from@xxx.xx");
        jobConfig.getProps().setProperty(EmailPropertiesConstants.TO, "to1@xxx.xx,to1@xxx.xx");
    }

}
