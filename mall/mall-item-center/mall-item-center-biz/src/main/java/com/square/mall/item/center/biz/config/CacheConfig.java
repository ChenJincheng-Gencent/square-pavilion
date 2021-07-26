package com.square.mall.item.center.biz.config;

import com.square.mall.cache.api.CacheFactory;
import com.square.mall.cache.api.CacheService;
import com.square.mall.cache.vo.CacheRegistryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  缓存配置
 *
 * @author Gencent
 * @date 2020/7/26
 */
@Configuration
@Slf4j
public class CacheConfig {

    @Value("${redis.item.center.host}")
    private String host;

    @Value("${redis.item.center.port}")
    private Integer port;

    @Value("${redis.item.center.password}")
    private String password;

    @Value("${redis.item.center.index}")
    private Integer index;

    @Value("${redis.item.center.timeout}")
    private int timeout;

    @Value("${redis.item.center.workModel}")
    private String workModel;


    @Bean
    public CacheService cacheService() {

        CacheRegistryVo cacheRegistryVo = new CacheRegistryVo();
        cacheRegistryVo.setAppSecret(password);
        cacheRegistryVo.setConnectionTimeout(timeout);
        cacheRegistryVo.setDbIndex(index);
        cacheRegistryVo.setHost(host);
        cacheRegistryVo.setPort(String.valueOf(port));
        cacheRegistryVo.setWorkModel(workModel);
        return CacheFactory.createCache("item-center", cacheRegistryVo);

    }

    @Bean
    public CacheService cacheService(@Qualifier("cacheService") CacheService cacheService) {
        return cacheService;
    }
}
