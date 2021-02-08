package com.square.mall.job.application.handler;

import com.square.mall.job.application.annotation.ScheduledTask;
import com.square.mall.job.application.util.TaskConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ElasticJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.Map;


@Configuration
@Slf4j
@Order(1)
public class ScheduledTaskBeanProcessor implements ApplicationListener<ApplicationStartedEvent> {

    private String prefix = "task.";

    @Autowired
    private Environment environment;

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;



    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigurableApplicationContext ctx = event.getApplicationContext();
        Map<String, Object> beanMap = ctx.getBeansWithAnnotation(ScheduledTask.class);
        try {
            for (Object confBean : beanMap.values()) {
                new ScheduleJobBootstrap(zookeeperRegistryCenter, (ElasticJob)confBean,
                    createJobConfiguration((Class<ElasticJob>)(confBean.getClass()))).schedule();
            }
        } catch (Exception e) {
            log.error("Exception: ", e);
        }

    }

    private JobConfiguration createJobConfiguration(Class<ElasticJob> taskClass) {

        ScheduledTask conf = taskClass.getAnnotation(ScheduledTask.class);
        String taskName = conf.name();
        log.info("taskName: {}", taskName);
        String cron = getEnvironmentStringValue(taskName, TaskConstants.CRON, conf.cron());
        log.info("cron: {}", cron);
        int shardingTotalCount = getEnvironmentIntValue(taskName, TaskConstants.SHARDING_TOTAL_COUNT, conf.shardingTotalCount());
        String shardingItemParameters = getEnvironmentStringValue(taskName, TaskConstants.SHARDING_ITEM_PARAMETERS, conf.shardingItemParameters());
        String description = getEnvironmentStringValue(taskName, TaskConstants.DESCRIPTION, conf.description());
        String jobParameter = getEnvironmentStringValue(taskName, TaskConstants.JOB_PARAMETER, conf.jobParameter());
        String jobExceptionHandler = getEnvironmentStringValue(taskName, TaskConstants.JOB_EXCEPTION_HANDLER, conf.jobExceptionHandler());
        String executorServiceHandler = getEnvironmentStringValue(taskName, TaskConstants.EXECUTOR_SERVICE_HANDLER, conf.executorServiceHandler());
        String jobShardingStrategyClass = getEnvironmentStringValue(taskName, TaskConstants.JOB_SHARDING_STRATEGY_CLASS,
            conf.jobShardingStrategyClass());
        String eventTraceRdbDataSource = getEnvironmentStringValue(taskName, TaskConstants.EVENT_TRACE_RDB_DATA_SOURCE,
            conf.eventTraceRdbDataSource());
        boolean failover = getEnvironmentBooleanValue(taskName, TaskConstants.FAILOVER, conf.failover());
        boolean misfire = getEnvironmentBooleanValue(taskName, TaskConstants.MISFIRE, conf.misfire());
        boolean overwrite = getEnvironmentBooleanValue(taskName, TaskConstants.OVERWRITE, conf.overwrite());
        boolean disabled = getEnvironmentBooleanValue(taskName, TaskConstants.DISABLED, conf.disabled());
        boolean monitorExecution = getEnvironmentBooleanValue(taskName, TaskConstants.MONITOR_EXECUTION, conf.monitorExecution());
        int monitorPort = getEnvironmentIntValue(taskName, TaskConstants.MONITOR_PORT, conf.monitorPort());
        boolean streamingProcess = getEnvironmentBooleanValue(taskName, TaskConstants.STREAMING_PROCESS, conf.streamingProcess());
        int maxTimeDiffSeconds = getEnvironmentIntValue(taskName, TaskConstants.MAX_TIME_DIFF_SECONDS, conf.maxTimeDiffSeconds());
        int reconcileIntervalMinutes = getEnvironmentIntValue(taskName, TaskConstants.RECONCILE_INTERVAL_MINUTES, conf.reconcileIntervalMinutes());

        JobConfiguration jobConfiguration = JobConfiguration.newBuilder(taskName, shardingTotalCount).cron(cron).build();
        return jobConfiguration;
    }



    /**
     * 获取配置中的任务属性值，environment没有就用注解中的值
     * @param jobName		任务名称
     * @param fieldName		属性名称
     * @param defaultValue  默认值
     * @return
     */
    private String getEnvironmentStringValue(String jobName, String fieldName, String defaultValue) {
        String key = prefix + jobName + "." + fieldName;
        String value = environment.getProperty(key);
        if (StringUtils.hasText(value)) {
            return value;
        }
        return defaultValue;
    }

    private int getEnvironmentIntValue(String jobName, String fieldName, int defaultValue) {
        String key = prefix + jobName + "." + fieldName;
        String value = environment.getProperty(key);
        if (StringUtils.hasText(value)) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }

    private long getEnvironmentLongValue(String jobName, String fieldName, long defaultValue) {
        String key = prefix + jobName + "." + fieldName;
        String value = environment.getProperty(key);
        if (StringUtils.hasText(value)) {
            return Long.valueOf(value);
        }
        return defaultValue;
    }

    private boolean getEnvironmentBooleanValue(String jobName, String fieldName, boolean defaultValue) {
        String key = prefix + jobName + "." + fieldName;
        String value = environment.getProperty(key);
        if (StringUtils.hasText(value)) {
            return Boolean.valueOf(value);
        }
        return defaultValue;
    }


}
