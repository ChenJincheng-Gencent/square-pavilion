package com.square.mall.member.application.config;

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

    @Value("${redis.member.application.host}")
    private String host;

    @Value("${redis.member.application.port}")
    private Integer port;

    @Value("${redis.member.application.password}")
    private String password;

    @Value("${redis.member.application.index}")
    private Integer index;

    @Value("${redis.member.application.timeout}")
    private int timeout;

    @Value("${redis.member.application.workModel}")
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
        return CacheFactory.createCache("member-application", cacheRegistryVo);

    }

    @Bean
    public CacheService cacheService(@Qualifier("cacheService") CacheService cacheService) {
        return cacheService;
    }
}
