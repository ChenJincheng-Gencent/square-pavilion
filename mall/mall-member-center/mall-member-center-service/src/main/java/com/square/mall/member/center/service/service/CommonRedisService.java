package com.square.mall.member.center.service.service;

import java.util.List;
import java.util.Set;

/**
 * 公共Redis Service接口
 *
 * @author Gencent
 * @date 2019/8/28
 */
public interface CommonRedisService {

    /**
     * 写入缓存
     *
     * @param index 库索引
     * @param key   键
     * @param value 值
     * @return boolean
     */
    boolean set(int index, final String key, Object value);


    /**
     * 写入缓存设置时效时间
     *
     * @param index      库索引
     * @param key        键
     * @param value      值
     * @param expireTime 失效时间，单位秒
     * @return boolean
     */
    boolean set(int index, final String key, Object value, long expireTime);


    /**
     * 批量删除对应的value
     *
     * @param index 库索引
     * @param keys  键列表
     */
    void remove(int index, final String... keys);

    /**
     * 批量删除key
     *
     * @param index   库索引
     * @param pattern 键列表
     */
    void removePattern(int index, final String pattern);


    /**
     * 删除对应的value
     *
     * @param index 库索引
     * @param key   键
     */
    void remove(int index, final String key);


    /**
     * 判断缓存中是否有对应的value
     *
     * @param index 库索引
     * @param key   键
     * @return boolean
     */
    boolean exists(int index, final String key);


    /**
     * 读取缓存
     *
     * @param index 库索引
     * @param key   键
     * @return Object
     */
    Object get(int index, final String key);


    /**
     * 哈希添加
     *
     * @param index   库索引
     * @param key     键
     * @param hashKey 哈希键
     * @param value   值
     */
    void hSet(int index, String key, Object hashKey, Object value);


    /**
     * 哈希添加
     *
     * @param index      库索引
     * @param key        键
     * @param hashKey    哈希键
     * @param value      值
     * @param expireTime 失效时间，单位秒
     */
    void hSet(int index, String key, Object hashKey, Object value, long expireTime);

    /**
     * 哈希获取数据
     *
     * @param index   库索引
     * @param key     键
     * @param hashKey 哈希键
     * @return Object
     */
    Object hGet(int index, String key, Object hashKey);


    /**
     * 列表添加
     *
     * @param index 库索引
     * @param key   键
     * @param value 值
     */
    void lPush(int index, String key, Object value);


    /**
     * 列表获取
     *
     * @param index 库索引
     * @param key   键
     * @param start 偏移量开始值
     * @param end   偏移量结束值
     * @return List<Object>
     */
    List<Object> lRange(int index, String key, long start, long end);


    /**
     * 集合添加
     *
     * @param index 库索引
     * @param key   键
     * @param value 值
     */
    void add(int index, String key, Object value);


    /**
     * 集合获取
     *
     * @param index 库索引
     * @param key   键
     * @return Set<Object>
     */
    Set<Object> setMembers(int index, String key);


    /**
     * 有序集合添加
     *
     * @param index 库索引
     * @param key   键
     * @param value 值
     * @param score 分数
     */
    void zAdd(int index, String key, Object value, double score);


    /**
     * 有序集合获取
     *
     * @param index    库索引
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @return Set<Object>
     */
    Set<Object> rangeByScore(int index, String key, double minScore, double maxScore);

    /**
     * 获取所有key
     *
     * @param index   库索引
     * @param pattern 模式
     * @return Set<String>
     */
    Set<String> keys(int index, final String pattern);

}
