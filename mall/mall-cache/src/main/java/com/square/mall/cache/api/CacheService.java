package com.square.mall.cache.api;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

public interface CacheService {

    String TYPE_REDIS = "REDIS";

    String TYPE_MEMCACHED = "MEMCACHED";

    String TYPE_ALIYUN_REDIS = "ALIYUN_REDIS";

    String TYPE_ALIYUN_MEMCACHE = "ALIYUN_MEMCACHE";

    boolean setCache(String paramString, Object paramObject);

    boolean setCache(String paramString, Object paramObject, int paramInt);

    boolean setCache(String paramString1, String paramString2, Object paramObject);

    boolean setCache(String paramString1, String paramString2, Object paramObject, int paramInt);

    boolean setPersistCache(String paramString, Object paramObject);

    boolean setPersistCache(String paramString1, String paramString2, Object paramObject);

    <T> T getCache(String paramString, Class<T> paramClass);

    <T> T getCache(String paramString1, String paramString2, Class<T> paramClass);

    boolean delCache(String paramString);

    boolean delCache(String paramString1, String paramString2);

    boolean add(String paramString, Object paramObject);

    boolean add(String paramString, Object paramObject, int paramInt);

    Long incr(String paramString);

    Long incr(String paramString, long paramLong);

    Long incrBy(String paramString, long paramLong);

    Long incrBy(String paramString, long paramLong1, long paramLong2);

    <T> void lpush(String paramString, List<T> paramList);

    <T> void lpush(String paramString, List<T> paramList, int paramInt);

    <T> void lpush(String paramString1, String paramString2, List<T> paramList);

    <T> void lpush(String paramString1, String paramString2, List<T> paramList, int paramInt);

    <T> void rpush(String paramString, List<T> paramList);

    <T> void rpush(String paramString, List<T> paramList, int paramInt);

    <T> void rpush(String paramString1, String paramString2, List<T> paramList);

    <T> void rpush(String paramString1, String paramString2, List<T> paramList, int paramInt);

    <T> List<T> getList(String paramString, Class<T> paramClass);

    <T> List<T> getList(String paramString1, String paramString2, Class<T> paramClass);

    <T> List<T> getList(String paramString, Integer paramInteger1, Integer paramInteger2, Class<T> paramClass);

    <T> List<T> getList(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Class<T> paramClass);

    <T> void hset(String paramString1, String paramString2, T paramT);

    <T> void hset(String paramString1, String paramString2, T paramT, int paramInt);

    <T> void hset(String paramString1, String paramString2, String paramString3, T paramT);

    <T> void hset(String paramString1, String paramString2, String paramString3, T paramT, int paramInt);

    <T> T hget(String paramString1, String paramString2, Class<T> paramClass);

    <T> T hget(String paramString1, String paramString2, String paramString3, Class<T> paramClass);

    <T> List<T> hget(String paramString1, String paramString2, TypeReference<List<T>> paramTypeReference);

    <T> List<T> hget(String paramString1, String paramString2, String paramString3, TypeReference<List<T>> paramTypeReference);

    <K, V> Map<K, V> hgetMap(String paramString1, String paramString2, TypeReference<Map<K, V>> paramTypeReference);

    <K, V> Map<K, V> hgetMap(String paramString1, String paramString2, String paramString3, TypeReference<Map<K, V>> paramTypeReference);

    boolean hdel(String paramString, String... paramVarArgs);

    boolean hdel(String paramString1, String paramString2, String... paramVarArgs);

    boolean expire(String paramString, int paramInt);

    boolean expire(String paramString1, String paramString2, int paramInt);

    Set<String> getKeys();

    Set<String> getKeys(String paramString);

    Set<String> getKeys(String paramString1, String paramString2);

    Long zadd(String paramString1, Long paramLong, String paramString2);

    Long zadd(String paramString1, Long paramLong, String paramString2, int paramInt);

    Long zadd(String paramString, Map<String, Long> paramMap);

    Long zadd(String paramString, Map<String, Long> paramMap, int paramInt);

    Long zadd(String paramString1, String paramString2, Long paramLong, String paramString3);

    Long zadd(String paramString1, String paramString2, Long paramLong, String paramString3, int paramInt);

    Long zadd(String paramString1, String paramString2, Map<String, Long> paramMap);

    Long zadd(String paramString1, String paramString2, Map<String, Long> paramMap, int paramInt);

    Set<String> zrange(String paramString);

    Set<String> zrange(String paramString1, String paramString2);

    Set<String> zrange(String paramString, Long paramLong1, Long paramLong2);

    Set<String> zrange(String paramString1, String paramString2, Long paramLong1, Long paramLong2);

    Map<Long, String> zrangeWithScores(String paramString);

    Map<Long, String> zrangeWithScores(String paramString1, String paramString2);

    Map<Long, String> zrangeWithScores(String paramString, Long paramLong1, Long paramLong2);

    Map<Long, String> zrangeWithScores(String paramString1, String paramString2, Long paramLong1, Long paramLong2);

    Set<String> zrevrange(String paramString);

    Set<String> zrevrange(String paramString1, String paramString2);

    Set<String> zrevrange(String paramString, Long paramLong1, Long paramLong2);

    Set<String> zrevrange(String paramString1, String paramString2, Long paramLong1, Long paramLong2);

    Map<Long, String> zrevrangeWithScores(String paramString);

    Map<Long, String> zrevrangeWithScores(String paramString1, String paramString2);

    Map<Long, String> zrevrangeWithScores(String paramString, Long paramLong1, Long paramLong2);

    Map<Long, String> zrevrangeWithScores(String paramString1, String paramString2, Long paramLong1, Long paramLong2);

    Long zrem(String paramString1, String paramString2);

    Long zrem(String paramString, List<String> paramList);

    Long zrem(String paramString1, String paramString2, String paramString3);

    Long zrem(String paramString1, String paramString2, List<String> paramList);

    Long zcard(String paramString);

    Long zcard(String paramString1, String paramString2);

    Long zscore(String paramString1, String paramString2);

    Long zscore(String paramString1, String paramString2, String paramString3);

    Long zcount(String paramString, Long paramLong1, Long paramLong2);

    Long zcount(String paramString1, String paramString2, String paramString3);

    Long zcount(String paramString1, String paramString2, Long paramLong1, Long paramLong2);

    Long zcount(String paramString1, String paramString2, String paramString3, String paramString4);

    ScanResult<Tuple> zscan(String paramString1, String paramString2);

    ScanResult<Tuple> zscan(String paramString1, String paramString2, String paramString3);

    Long setnx(String paramString1, String paramString2);

    Long setnx(String paramString1, String paramString2, String paramString3);

    Transaction multi();

    void shutdown();

    String watch(String... paramVarArgs);

    void unwatch();
}
