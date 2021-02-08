package com.square.mall.job.application.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.infra.listener.ElasticJobListener;
import org.apache.shardingsphere.elasticjob.infra.listener.ShardingContexts;

/**
 *〈ScheduledTaskListener〉<br>
 *
 * @author number68
 * @date 2019/4/24
 * @since 0.1
 */
@Slf4j
public class SimpleJobListener implements ElasticJobListener {

    public SimpleJobListener() {}

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        log.info("Job:{} shards are starting execution.", shardingContexts.getJobName());
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("Congratulations, Job:{} shards have been finished successfully in this instance.",
            shardingContexts.getJobName());
    }

    @Override
    public String getType() {
        return "simpleJobListener";
    }
}
