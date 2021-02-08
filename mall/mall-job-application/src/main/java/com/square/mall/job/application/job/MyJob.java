package com.square.mall.job.application.job;

import com.square.mall.job.application.annotation.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Slf4j
@ScheduledTask(name = "MyJob", cron = "*/5 * * * * ?", shardingTotalCount = 2, overwrite = true)
public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                break;
            case 1:
                // do something by sharding item 1
                break;
            case 2:
                // do something by sharding item 2
                break;
            // case n: ...
        }
        log.info(String.format("Item777777777777: %s | Time: %s | Thread: %s | %s",
                context.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()), Thread
                    .currentThread().getId(), "SIMPLE"));
    }
}
