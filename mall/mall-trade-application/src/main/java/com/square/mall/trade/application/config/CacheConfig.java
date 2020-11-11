package com.square.mall.trade.application.config;

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

    @Value("${redis.trade.application.host}")
    private String host;

    @Value("${redis.trade.application.port}")
    private Integer port;

    @Value("${redis.trade.application.password}")
    private String password;

    @Value("${redis.trade.application.index}")
    private Integer index;

    @Value("${redis.trade.application.timeout}")
    private int timeout;

    @Value("${redis.trade.application.workModel}")
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
        return CacheFactory.createCache("trade-application", cacheRegistryVo);

    }

    @Bean
    public CacheService cacheService(@Qualifier("cacheService") CacheService cacheService) {
        return cacheService;
    }
}
