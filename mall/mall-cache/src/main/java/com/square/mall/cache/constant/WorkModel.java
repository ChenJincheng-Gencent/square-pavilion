package com.square.mall.cache.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 运行模式枚举
 *
 * @author Gencent
 * @date 2020/7/14
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WorkModel {

    /**
     * 单例
     */
    SINGLE("single"),

    /**
     * 集群
     */
    CLUSTER("cluster"),

    /**
     * 分片
     */
    SHARDING("sharding");

    private String name;

}
