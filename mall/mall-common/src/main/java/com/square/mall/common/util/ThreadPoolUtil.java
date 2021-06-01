package com.square.mall.common.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * Async 使用默认线程池
 *  利特尔法则（Little’s law）：一个系统请求数等于请求的到达率与平均每个单独请求花费的时间之乘积。
 *
 * 依托利特尔法则通常根据3个要素来确定线程池参数设置；
 *
 * （1）tasks ：每秒的任务数，假设为500~1000
 *
 * （2）taskCost：每个任务花费时间，假设为0.1s
 *
 * （3）responseTime：系统允许容忍的最大响应时间，假设为1s       
 *
 * 另外基于实践有以下几个配置公式：
 * 1、corePoolSize = 每秒需要多少个线程处理=tasks/(1/taskCost) =tasks*taskCost
 *
 * 2、queueCapacity = (coreSizePool/taskCost)*responseTime
 *
 * 3、maxPoolSize = (max(tasks)- queueCapacity)/(1/taskCost)
 *
 */

/**
 *  线程池工具类
 *
 * @author Gencent
 * @date 2020/7/18
 */
@Component
public class ThreadPoolUtil {

    /**
     * CPU核数
     */
    private final static int CORE_NUM = Runtime.getRuntime().availableProcessors();

    /**
     * 每秒的任务数
     */
    private final static int TASKS = 50;

    /**
     * 每个任务花费时间,单位秒
     */
    private final static double TASK_COST = 0.2;

    /**
     * 系统允许容忍的最大响应时间，单位秒
     */
    private final static int RESPONSE_TIME = 1;

    /**
     * 核心线程数
     */
    private final static int CORE_POOL_SIZE = Math.max((int)(TASKS * TASK_COST), CORE_NUM);

    /**
     * 队列容量
     */
    private final static int QUEUE_CAPACITY = (int)(CORE_POOL_SIZE /TASK_COST * RESPONSE_TIME);

    /**
     * 最大线程数
     */
    private final static int MAX_POOL_SIZE = Math.max((int)((2 * TASKS - QUEUE_CAPACITY) * TASK_COST), CORE_POOL_SIZE);

    /**
     * 空闲线程回收时间间隔
     */
    private final static int KEEP_ALIVE_TIME = 10;

    @Bean("AsyncThreadPool")
    public Executor createAsyncThreadPool() {
        return new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                this.asyncThreadFactory());
    }

    /**
     * 异步线程工厂
     *
     * @return 线程工厂
     */
    private ThreadFactory asyncThreadFactory() {
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        return threadFactoryBuilder.setNameFormat("AsyncThread-%d").build();
    }


}
