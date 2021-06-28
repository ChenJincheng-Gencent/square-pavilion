package com.square.mall.cache.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.square.mall.cache.vo.CacheRegistryVo;

import java.util.*;

/**
 *  缓存服务抽象类
 *
 * @author Gencent
 * @date 2020/7/14
 */
public abstract class AbstractCacheService implements CacheService {

    protected CacheRegistryVo cacheRegistryVo;

    protected String group;

    public void init(String group, CacheRegistryVo cacheRegistryVo) {
        this.cacheRegistryVo = cacheRegistryVo;
        this.group = group;
    }


    @Override
    public boolean setCache(String key, Object value) {
        return setCache(this.group, key, value, 3000);
    }





    @Override
    public boolean setCache(String group, String key, Object value) { return setCache(group, key, value, 3000); }





    @Override
    public boolean setCache(String key, Object value, int seconds) { return setCache(this.group, key, value, seconds); }





    @Override
    public boolean setPersistCache(String key, Object value) { return setPersistCache(this.group, key, value); }





    @Override
    public <T> T getCache(String key, Class<T> clz) { return getCache(this.group, key, clz); }





    @Override
    public boolean delCache(String key) { return delCache(this.group, key); }




    @Override
    public boolean add(String key, Object value) { return add(key, value, 3000); }



    @Override
    public Long incr(String key) { return incr(key, 0L); }



    @Override
    public Long incrBy(String key, long count) { return incrBy(key, count, 0L); }




    @Override
    public <T> void lpush(String key, List<T> value) { lpush(this.group, key, value, 0); }




    @Override
    public <T> void lpush(String key, List<T> value, int seconds) { lpush(this.group, key, value, seconds); }




    @Override
    public <T> void lpush(String group, String key, List<T> value) { lpush(group, key, value, 0); }




    @Override
    public <T> void rpush(String key, List<T> value) { rpush(this.group, key, value, 0); }




    @Override
    public <T> void rpush(String key, List<T> value, int seconds) { rpush(this.group, key, value, seconds); }




    @Override
    public <T> void rpush(String group, String key, List<T> value) { rpush(group, key, value, 0); }




    @Override
    public <T> List<T> getList(String key, Class<T> clz) {
        return getList(this.group, key, 0, -1, clz);
    }




    @Override
    public <T> List<T> getList(String group, String key, Class<T> clz) {
        return getList(group, key, 0, -1, clz);
    }




    @Override
    public <T> List<T> getList(String key, Integer from, Integer to, Class<T> clz) {
        return getList(this.group, key, from, to, clz);
    }




    @Override
    public <T> void hset(String key, String field, T value) { hset(this.group, key, field, value, 0); }




    @Override
    public <T> void hset(String group, String key, String field, T value) { hset(group, key, field, value, 0); }




    @Override
    public <T> void hset(String key, String field, T value, int seconds) { hset(this.group, key, field, value, seconds); }




    @Override
    public <T> T hget(String key, String field, Class<T> clz) {
        return hget(this.group, key, field, clz);
    }




    @Override
    public <T> List<T> hget(String key, String field, TypeReference<List<T>> typeReference) { return hget(this.group, key, field, typeReference); }




    @Override
    public <K, V> Map<K, V> hgetMap(String key, String field, TypeReference<Map<K, V>> typeReference) { return hgetMap(this.group, key, field, typeReference); }




    @Override
    public boolean hdel(String key, String... field) { return hdel(this.group, key, field); }



    @Override
    public Long zadd(String key, Long score, String member) {
        Map<String, Long> scoreMembers = new HashMap<>();
        scoreMembers.put(member, score);
        return zadd(this.group, key, scoreMembers);
    }



    @Override
    public Long zadd(String key, Map<String, Long> scoreMembers) {
        return zadd(this.group, key, scoreMembers);
    }



    @Override
    public Long zadd(String group, String key, Long score, String member) {
        Map<String, Long> scoreMembers = new HashMap<>();
        scoreMembers.put(member, score);
        return zadd(group, key, scoreMembers);
    }


    @Override
    public Long zadd(String key, Long score, String member, int seconds) {
        Map<String, Long> scoreMembers = new HashMap<>();
        scoreMembers.put(member, score);
        return zadd(this.group, key, scoreMembers, seconds);
    }



    @Override
    public Long zadd(String key, Map<String, Long> scoreMembers, int seconds) {
        return zadd(this.group, key, scoreMembers, seconds);
    }



    @Override
    public Long zadd(String group, String key, Long score, String member, int seconds) {
        Map<String, Long> scoreMembers = new HashMap<>();
        scoreMembers.put(member, score);
        return zadd(group, key, scoreMembers, seconds);
    }



    @Override
    public Set<String> zrange(String key) {
        return zrange(this.group, key, 0L, -1L);
    }




    @Override
    public Set<String> zrange(String group, String key) {
        return zrange(group, key, 0L, 1L);
    }




    @Override
    public Set<String> zrange(String key, Long start, Long end) {
        return zrange(this.group, key, start, end);
    }




    @Override
    public Map<Long, String> zrangeWithScores(String key) {
        return zrangeWithScores(this.group, key, 0L, -1L);
    }




    @Override
    public Map<Long, String> zrangeWithScores(String group, String key) {
        return zrangeWithScores(group, key, 0L, -1L);
    }




    @Override
    public Map<Long, String> zrangeWithScores(String key, Long start, Long end) {
        return zrangeWithScores(this.group, key, start, end);
    }




    @Override
    public Set<String> zrevrange(String key) {
        return zrevrange(this.group, key, 0L, -1L);
    }




    @Override
    public Set<String> zrevrange(String group, String key) {
        return zrevrange(group, key, 0L, -1L);
    }




    @Override
    public Set<String> zrevrange(String key, Long start, Long end) {
        return zrevrange(this.group, key, start, end);
    }




    @Override
    public Map<Long, String> zrevrangeWithScores(String key) {
        return zrevrangeWithScores(this.group, key, 0L, -1L);
    }




    @Override
    public Map<Long, String> zrevrangeWithScores(String group, String key) {
        return zrevrangeWithScores(group, key, 0L, -1L);
    }




    @Override
    public Map<Long, String> zrevrangeWithScores(String key, Long start, Long end) {
        return zrevrangeWithScores(this.group, key, start, end);
    }



    @Override
    public Long zrem(String key, String member) {
        List<String> list = new ArrayList<>();
        list.add(member);
        return zrem(this.group, key, list);
    }


    @Override
    public Long zrem(String key, List<String> members) {
        return zrem(this.group, key, members);
    }


    @Override
    public Long zrem(String group, String key, String member) {
        List<String> list = new ArrayList<>();
        list.add(member);
        return zrem(group, key, list);
    }




    @Override
    public Long zcard(String key) {
        return zcard(this.group, key);
    }




    @Override
    public Long zscore(String key, String members) { return zscore(this.group, key, members); }




    @Override
    public Long zcount(String key, Long min, Long max) { return zcount(this.group, key, min, max); }




    @Override
    public Long zcount(String key, String min, String max) { return zcount(this.group, key, min, max); }




    @Override
    public Set<String> getKeys() { return getKeys(this.group, "*"); }




    @Override
    public Set<String> getKeys(String pattern) { return getKeys(this.group, pattern); }




    @Override
    public boolean expire(String key, int seconds) { return expire(this.group, key, seconds); }

    @Override
    public Long setnx(String key, String value) { return setnx(this.group, key, value); }



    public String combineKey(String key) { return this.group + ":" + key; }


    public String combineKey(String group, String key) {
        if (null != group && !group.isEmpty()) {
            return group + ":" + key;
        }
        return key;
    }



    public String getGroup() { return this.group; }
}
