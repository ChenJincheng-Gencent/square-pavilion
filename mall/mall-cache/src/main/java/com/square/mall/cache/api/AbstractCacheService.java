package com.square.mall.cache.api;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.square.mall.cache.vo.CacheRegistryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public abstract class AbstractCacheService implements CacheService {
    public static final int CONNECTION_TIMEOUT = 3000;


    protected CacheRegistryVo cacheRegistryVo;
    protected String group;

    public void init(String group, CacheRegistryVo cacheRegistryVo) {
        this.cacheRegistryVo = cacheRegistryVo;
        this.group = group;
    }




    public boolean setCache(String key, Object value) { return setCache(this.group, key, value, 3000); }





    public boolean setCache(String group, String key, Object value) { return setCache(group, key, value, 3000); }





    public boolean setCache(String key, Object value, int seconds) { return setCache(this.group, key, value, seconds); }





    public boolean setPersistCache(String key, Object value) { return setPersistCache(this.group, key, value); }





    public <T> T getCache(String key, Class<T> clz) { return (T)getCache(this.group, key, clz); }





    public boolean delCache(String key) { return delCache(this.group, key); }




    public boolean add(String key, Object value) { return add(key, value, 3000); }



    public Long incr(String key) { return incr(key, 0L); }



    public Long incrBy(String key, long count) { return incrBy(key, count, 0L); }




    public <T> void lpush(String key, List<T> value) { lpush(this.group, key, value, 0); }




    public <T> void lpush(String key, List<T> value, int seconds) { lpush(this.group, key, value, seconds); }




    public <T> void lpush(String group, String key, List<T> value) { lpush(group, key, value, 0); }




    public <T> void rpush(String key, List<T> value) { rpush(this.group, key, value, 0); }




    public <T> void rpush(String key, List<T> value, int seconds) { rpush(this.group, key, value, seconds); }




    public <T> void rpush(String group, String key, List<T> value) { rpush(group, key, value, 0); }




    public <T> List<T> getList(String key, Class<T> clz) { return getList(this.group, key, Integer.valueOf(0), Integer.valueOf(-1), clz); }




    public <T> List<T> getList(String group, String key, Class<T> clz) { return getList(group, key, Integer.valueOf(0), Integer.valueOf(-1), clz); }




    public <T> List<T> getList(String key, Integer from, Integer to, Class<T> clz) { return getList(this.group, key, from, to, clz); }




    public <T> void hset(String key, String field, T value) { hset(this.group, key, field, value, 0); }




    public <T> void hset(String group, String key, String field, T value) { hset(group, key, field, value, 0); }




    public <T> void hset(String key, String field, T value, int seconds) { hset(this.group, key, field, value, seconds); }




    public <T> T hget(String key, String field, Class<T> clz) { return (T)hget(this.group, key, field, clz); }




    public <T> List<T> hget(String key, String field, TypeReference<List<T>> typeReference) { return hget(this.group, key, field, typeReference); }




    public <K, V> Map<K, V> hgetMap(String key, String field, TypeReference<Map<K, V>> typeReference) { return hgetMap(this.group, key, field, typeReference); }




    public boolean hdel(String key, String... field) { return hdel(this.group, key, field); }



    public Long zadd(String key, Long score, String member) {
        Map<String, Long> scoreMembers = new HashMap<String, Long>();
        scoreMembers.put(member, score);
        return zadd(this.group, key, scoreMembers);
    }



    public Long zadd(String key, Map<String, Long> scoreMembers) { return zadd(this.group, key, scoreMembers); }



    public Long zadd(String group, String key, Long score, String member) {
        Map<String, Long> scoreMembers = new HashMap<String, Long>();
        scoreMembers.put(member, score);
        return zadd(group, key, scoreMembers);
    }


    public Long zadd(String key, Long score, String member, int seconds) {
        Map<String, Long> scoreMembers = new HashMap<String, Long>();
        scoreMembers.put(member, score);
        return zadd(this.group, key, scoreMembers, seconds);
    }



    public Long zadd(String key, Map<String, Long> scoreMembers, int seconds) { return zadd(this.group, key, scoreMembers, seconds); }



    public Long zadd(String group, String key, Long score, String member, int seconds) {
        Map<String, Long> scoreMembers = new HashMap<String, Long>();
        scoreMembers.put(member, score);
        return zadd(group, key, scoreMembers, seconds);
    }



    public Set<String> zrange(String key) { return zrange(this.group, key, Long.valueOf(0L), Long.valueOf(-1L)); }




    public Set<String> zrange(String group, String key) { return zrange(group, key, Long.valueOf(0L), Long.valueOf(-1L)); }




    public Set<String> zrange(String key, Long start, Long end) { return zrange(this.group, key, start, end); }




    public Map<Long, String> zrangeWithScores(String key) { return zrangeWithScores(this.group, key, Long.valueOf(0L), Long.valueOf(-1L)); }




    public Map<Long, String> zrangeWithScores(String group, String key) { return zrangeWithScores(group, key, Long.valueOf(0L), Long.valueOf(-1L)); }




    public Map<Long, String> zrangeWithScores(String key, Long start, Long end) { return zrangeWithScores(this.group, key, start, end); }




    public Set<String> zrevrange(String key) { return zrevrange(this.group, key, Long.valueOf(0L), Long.valueOf(-1L)); }




    public Set<String> zrevrange(String group, String key) { return zrevrange(group, key, Long.valueOf(0L), Long.valueOf(-1L)); }




    public Set<String> zrevrange(String key, Long start, Long end) { return zrevrange(this.group, key, start, end); }




    public Map<Long, String> zrevrangeWithScores(String key) { return zrevrangeWithScores(this.group, key, Long.valueOf(0L), Long.valueOf(-1L)); }




    public Map<Long, String> zrevrangeWithScores(String group, String key) { return zrevrangeWithScores(group, key, Long.valueOf(0L), Long.valueOf(-1L)); }




    public Map<Long, String> zrevrangeWithScores(String key, Long start, Long end) { return zrevrangeWithScores(this.group, key, start, end); }



    public Long zrem(String key, String member) {
        List<String> list = new ArrayList<String>();
        list.add(member);
        return zrem(this.group, key, list);
    }


    public Long zrem(String key, List<String> members) { return zrem(this.group, key, members); }


    public Long zrem(String group, String key, String member) {
        List<String> list = new ArrayList<String>();
        list.add(member);
        return zrem(group, key, list);
    }




    public Long zcard(String key) { return zcard(this.group, key); }




    public Long zscore(String key, String members) { return zscore(this.group, key, members); }




    public Long zcount(String key, Long min, Long max) { return zcount(this.group, key, min, max); }




    public Long zcount(String key, String min, String max) { return zcount(this.group, key, min, max); }




    public Set<String> getKeys() { return getKeys(this.group, "*"); }




    public Set<String> getKeys(String pattern) { return getKeys(this.group, pattern); }




    public boolean expire(String key, int seconds) { return expire(this.group, key, seconds); }




    public Long setnx(String key, String value) { return setnx(this.group, key, value); }



    public String combineKey(String key) { return this.group + ":" + key; }


    public String combineKey(String group, String key) {
        if (StringUtils.isNotBlank(group)) {
            return group + ":" + key;
        }
        return key;
    }



    public String getGroup() { return this.group; }
}
