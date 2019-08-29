package com.square.mall.member.center.service.service.impl;

import com.square.mall.member.center.service.service.CommonRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis Service接口实现类
 *
 * @author Gencent
 * @date 2019/8/29
 */

@Slf4j
@Service
public class CommonRedisServiceImpl implements CommonRedisService {

    @Resource(name = "memberRedisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * 指定redis库
     *
     * @param index 库索引
     * @return RedisTemplate
     */
    private RedisTemplate getTemplate(int index) {

        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        if (null != jedisConnectionFactory) {
            RedisStandaloneConfiguration config = jedisConnectionFactory.getStandaloneConfiguration();
            if (null != config) {
                config.setDatabase(index);
                redisTemplate.setConnectionFactory(jedisConnectionFactory);
            }
        }

        return redisTemplate;

    }


    @Override
    public boolean set(int index, final String key, Object value) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return false;
        }

        RedisTemplate template = getTemplate(index);
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = template.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return result;

    }


    @Override
    public boolean set(int index, final String key, Object value, long expireTime) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return false;
        }

        RedisTemplate template = getTemplate(index);
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = template.opsForValue();
            operations.set(key, value);
            template.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return result;

    }


    @Override
    public void remove(int index, final String... keys) {

        if (null == keys) {
            log.error("keys is null.");
            return;
        }

        for (String key : keys) {
            remove(index, key);
        }

    }


    @Override
    public void removePattern(int index, final String pattern) {

        if (StringUtils.isBlank(pattern)) {
            log.error("pattern is null or empty.");
            return;
        }

        RedisTemplate template = getTemplate(index);
        Set<Serializable> keys = template.keys(pattern);
        if (null != keys && keys.size() > 0) {
            template.delete(keys);
        }

    }


    @Override
    public void remove(int index, final String key) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return;
        }

        RedisTemplate template = getTemplate(index);
        if (exists(index, key)) {
            template.delete(key);
        }

    }


    @Override
    public boolean exists(int index, final String key) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return false;
        }

        RedisTemplate template = getTemplate(index);
        return template.hasKey(key);

    }


    @Override
    public Object get(int index, final String key) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return null;
        }

        RedisTemplate template = getTemplate(index);
        ValueOperations<Serializable, Object> operations = template.opsForValue();

        return operations.get(key);

    }


    @Override
    public void hSet(int index, String key, Object hashKey, Object value) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return;
        }

        RedisTemplate template = getTemplate(index);
        HashOperations<String, Object, Object> hash = template.opsForHash();
        hash.put(key, hashKey, value);

    }

    @Override
    public void hSet(int index, String key, Object hashKey, Object value, long expireTime) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return;
        }

        RedisTemplate template = getTemplate(index);
        HashOperations<String, Object, Object> hash = template.opsForHash();
        template.expire(key, expireTime, TimeUnit.SECONDS);
        hash.put(key, hashKey, value);

    }


    @Override
    public Object hGet(int index, String key, Object hashKey) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return null;
        }

        RedisTemplate template = getTemplate(index);
        HashOperations<String, Object, Object> hash = template.opsForHash();

        return hash.get(key, hashKey);

    }


    @Override
    public void lPush(int index, String key, Object value) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return;
        }

        RedisTemplate template = getTemplate(index);
        ListOperations<String, Object> list = template.opsForList();
        list.rightPush(key, value);

    }


    @Override
    public List<Object> lRange(int index, String key, long start, long end) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return null;
        }

        RedisTemplate template = getTemplate(index);
        ListOperations<String, Object> list = template.opsForList();
        return list.range(key, start, end);

    }


    @Override
    public void add(int index, String key, Object value) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return;
        }

        RedisTemplate template = getTemplate(index);
        SetOperations<String, Object> set = template.opsForSet();
        set.add(key, value);

    }


    @Override
    public Set<Object> setMembers(int index, String key) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return null;
        }

        RedisTemplate template = getTemplate(index);
        SetOperations<String, Object> set = template.opsForSet();

        return set.members(key);

    }


    @Override
    public void zAdd(int index, String key, Object value, double score) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return;
        }

        RedisTemplate template = getTemplate(index);
        ZSetOperations<String, Object> zSet = template.opsForZSet();
        zSet.add(key, value, score);

    }


    @Override
    public Set<Object> rangeByScore(int index, String key, double minScore, double maxScore) {

        if (StringUtils.isBlank(key)) {
            log.error("key is null or empty.");
            return null;
        }

        RedisTemplate template = getTemplate(index);
        ZSetOperations<String, Object> zSet = template.opsForZSet();

        return zSet.rangeByScore(key, minScore, maxScore);

    }

    @Override
    public Set<String> keys(int index, String pattern) {

        if (StringUtils.isBlank(pattern)) {
            log.error("pattern is null or empty.");
            return null;
        }

        RedisTemplate template = getTemplate(index);
        Set<String> keys = template.keys(pattern);

        return keys;

    }


}
