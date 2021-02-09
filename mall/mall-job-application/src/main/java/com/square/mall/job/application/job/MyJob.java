package com.square.mall.job.application.job;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.BrandApi;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.job.application.annotation.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时器示例
 *
 * @author Gencent
 * @date 2021/2/8
 */
@Slf4j
@ScheduledTask(name = "MyJob", cron = "*/5 * * * * ?", shardingTotalCount = 2, overwrite = true)
public class MyJob implements SimpleJob {

    @Resource
    private BrandApi brandApi;

    @Override
    public void execute(ShardingContext context) {

        log.info("MyJob 定时任务执行开始");
        RspDto<List<BrandDto>> brandRspDto = brandApi.selectBrandAll();
        log.info("brandDtoList: {}", brandRspDto.getData());
        log.info("MyJob 定时任务执行结束");

    }
}
