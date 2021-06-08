package com.square.mall.gateway.config;

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
 * @date 2020/7/13
 */
@Configuration
@Slf4j
public class CacheConfig {

    @Value("${redis.gateway.host}")
    private String host;

    @Value("${redis.gateway.port}")
    private Integer port;

    @Value("${redis.gateway.password}")
    private String password;

    @Value("${redis.gateway.index}")
    private Integer index;

    @Value("${redis.gateway.timeout}")
    private int timeout;

    @Value("${redis.gateway.workModel}")
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
        return CacheFactory.createCache("gateway", cacheRegistryVo);

    }

    @Bean
    public CacheService cacheService(@Qualifier("cacheService") CacheService cacheService) {
        return cacheService;
    }
}
