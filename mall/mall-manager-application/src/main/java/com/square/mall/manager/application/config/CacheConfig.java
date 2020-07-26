package com.square.mall.manager.application.config;

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

    @Value("${redis.manager.application.host}")
    private String host;

    @Value("${redis.manager.application.port}")
    private Integer port;

    @Value("${redis.manager.application.password}")
    private String password;

    @Value("${redis.manager.application.index}")
    private Integer index;

    @Value("${redis.manager.application.timeout}")
    private int timeout;

    @Value("${redis.manager.application.workModel}")
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
        return CacheFactory.createCache("manager-application", cacheRegistryVo);

    }

    @Bean
    public CacheService cacheService(@Qualifier("cacheService") CacheService cacheService) {
        return cacheService;
    }
}
