package com.square.mall.job.application.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;

import java.util.ArrayList;
import java.util.List;

public class MyDataflowJob implements DataflowJob<String> {

    @Override
    public List<String> fetchData(ShardingContext context) {
        List<String> data = new ArrayList<>();
        switch (context.getShardingItem()) {
            case 0:
                data.add("item0");// get data from database by sharding item 0
                return data;
            case 1:
                data.add("item1"); // get data from database by sharding item 1
                return data;
            case 2:
                data.add("item2");// get data from database by sharding item 2
                return data;
            default:
                return data;
        }
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> data) {
        // process data
        // ...
    }
}