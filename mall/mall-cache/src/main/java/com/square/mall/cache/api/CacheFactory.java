package com.square.mall.cache.api;

import com.square.mall.cache.vo.CacheRegistryVo;

/**
 * 缓存工厂类
 *
 * @author Gencent
 * @date 2020/7/14
 */
public class CacheFactory {

    /**
     * 创建缓存实例
     * @param group 组别
     * @param cacheRegistryVo 缓存注册信息
     * @return 缓存实例
     */
    public static CacheService createCache(String group, CacheRegistryVo cacheRegistryVo) {

        RedisCache redisCache = new RedisCache();
        redisCache.init(group, cacheRegistryVo);
        return redisCache;

    }

}
