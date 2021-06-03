package com.square.mall.member.center.biz.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;

/**
 * Redis数据源配置
 *
 * @author Gencent
 * @date 2019/8/28
 */

@Configuration
@Slf4j
public class RedisCacheConfig {

    @Value("${redis.member.host}")
    private String host;

    @Value("${redis.member.port}")
    private Integer port;

    @Value("${redis.member.password}")
    private String password;

    @Value("${redis.member.index}")
    private Integer index;

    @Value("${redis.member.timeout}")
    private int timeout;

    @Value("${redis.member.pool.max-total}")
    private int maxTotal;

    @PostConstruct
    public void init() {
        log.info("member redis host {}, port {}", this.host, this.port);
    }

    @Primary
    @Bean(name = "memberRedisConnectionFactory")
    public JedisConnectionFactory memberRedisConnectionFactory() {

        if (StringUtils.isBlank(host) || StringUtils.isBlank(password)) {
            log.error("member redis host or password is blank.");
            return null;
        }
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setDatabase(index);
        config.setPassword(password);

        return new JedisConnectionFactory(config);

    }

    @Primary
    @Bean(name = "memberRedisTemplate")
    public RedisTemplate<String, String> memberRedisTemplate(@Qualifier("memberRedisConnectionFactory")
                                                                     RedisConnectionFactory cacheRedisConnectionFactory) {
        if (null == cacheRedisConnectionFactory) {
            return null;
        }
        StringRedisTemplate template = new StringRedisTemplate(cacheRedisConnectionFactory);
        template.afterPropertiesSet();
        log.warn("set member redis {}:{}", this.host, this.port);
        return template;

    }

}
