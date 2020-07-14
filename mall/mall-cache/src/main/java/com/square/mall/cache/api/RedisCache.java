package com.square.mall.cache.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.square.mall.cache.constant.WorkModel;
import com.square.mall.cache.vo.CacheRegistryVo;
import com.square.mall.common.exception.BusinessException;
import com.square.mall.common.util.StringUtil;
import com.square.mall.common.util.SymbolConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.*;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.Hashing;
import redis.clients.jedis.util.Sharded;

import java.util.*;

@Slf4j
public class RedisCache extends AbstractCacheService {

    private static ThreadLocal<Jedis> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<ShardedJedis> shardedThreadLocal = new ThreadLocal<>();

    private JedisPool jedisPool;

    private ShardedJedisPool shardedJedisPool;

    private JedisCluster jedisCluster;

    private int dbIndex;

    @Override
    public void init(String group, CacheRegistryVo cacheRegistryVo) {
        log.info("group:{}, redis配置:{}", group, cacheRegistryVo);
        super.init(group, cacheRegistryVo);
        checkAddress(cacheRegistryVo.getAddresses());
        dbIndex = cacheRegistryVo.getDbIndex();
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        setConfig(poolConfig);

        if (WorkModel.SINGLE.getName().equalsIgnoreCase(cacheRegistryVo.getWorkModel())) {
            String address = cacheRegistryVo.getAddresses()[0];
            String host = address.split(SymbolConstant.COLON)[0];
            String port = address.split(SymbolConstant.COLON)[1];
            jedisPool = new JedisPool(poolConfig, host, Integer.parseInt(port), cacheRegistryVo.getConnectionTimeout(),
                cacheRegistryVo.getAppSecret(), dbIndex);
        } else if (WorkModel.CLUSTER.getName().equalsIgnoreCase(cacheRegistryVo.getWorkModel())) {
            jedisCluster = new JedisCluster(getHostAndPortSet(cacheRegistryVo), cacheRegistryVo.getConnectionTimeout(),
                cacheRegistryVo.getSoTimeout(), cacheRegistryVo.getMaxAttempts(), cacheRegistryVo.getAppSecret(), poolConfig);
        }
        else if (WorkModel.SHARDING.getName().equalsIgnoreCase(cacheRegistryVo.getWorkModel())) {
            shardedJedisPool = new ShardedJedisPool(poolConfig, getShardInfoList(cacheRegistryVo), Hashing.MURMUR_HASH,
                Sharded.DEFAULT_KEY_TAG_PATTERN);
        } else {
            throw new BusinessException("初始化RedisCache找不到运行模式。workModel:"+cacheRegistryVo.getWorkModel());
        }
    }

    private void checkAddress(String[] addresses) {
        if (addresses == null) {
            throw new BusinessException("连接RedisCache地址不能为空");
        }
    }

    private Set<HostAndPort> getHostAndPortSet(CacheRegistryVo cacheRegistryVo) {
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        if (cacheRegistryVo.getAddresses() != null) {
            String[] addrs = cacheRegistryVo.getAddresses();
            for (int i = 0; i < addrs.length; i++) {
                HostAndPort hostAndPort = new HostAndPort(getHost(addrs[i]), getPort(addrs[i]));
                hostAndPortSet.add(hostAndPort);
            }
        } else {
            HostAndPort hostAndPort = new HostAndPort(cacheRegistryVo.getHost(), Integer.parseInt(cacheRegistryVo.getPort()));
            hostAndPortSet.add(hostAndPort);
        }
        return hostAndPortSet;
    }

    private List<JedisShardInfo> getShardInfoList(CacheRegistryVo cacheRegistryVo) {
        List<JedisShardInfo> shardsList = new ArrayList<>();
        if (cacheRegistryVo.getAddresses() != null) {
            String[] addrs = cacheRegistryVo.getAddresses();
            for (int i = 0; i < addrs.length; i++) {
                addShardInfo(getHost(addrs[i]), getPort(addrs[i]), cacheRegistryVo.getAppSecret(), shardsList);
            }
        } else {
            addShardInfo(cacheRegistryVo.getHost(), Integer.parseInt(cacheRegistryVo.getPort()), cacheRegistryVo
                .getAppSecret(), shardsList);
        }
        return shardsList;
    }

    private void addShardInfo(String host, Integer port, String appSecret, List<JedisShardInfo> shardsList) {
        StringBuilder uriHost = (new StringBuilder("redis://")).append(host).append(":").append(port);
        if (this.dbIndex > 0) {
            uriHost.append("/").append(this.dbIndex);
        }
        JedisShardInfo jedisShardInfo = new JedisShardInfo(uriHost.toString());
        if (StringUtils.isNotEmpty(appSecret)) {
            jedisShardInfo.setPassword(appSecret);
        }
        jedisShardInfo.setConnectionTimeout(3000);
        jedisShardInfo.setSoTimeout(3000);
        shardsList.add(jedisShardInfo);
    }


    private String getHost(String val) { return val.substring(0, val.indexOf(":")); }



    private int getPort(String val) { return Integer.parseInt(val.substring(val.indexOf(":") + 1)); }



    private void setConfig(GenericObjectPoolConfig config) {
        config.setMaxIdle(cacheRegistryVo.getMaxIdle());

        config.setMaxTotal(cacheRegistryVo.getMaxTotal());
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        config.setMaxWaitMillis(cacheRegistryVo.getMaxWaitMillis());
    }

    private Jedis getJedis() {
        if (jedisPool == null) {
            return null;
        }
        return jedisPool.getResource();
    }


    private ShardedJedis getShardedJedis() {
        if (shardedJedisPool == null) {
            return null;
        }
        return shardedJedisPool.getResource();
    }


    @Override
    public boolean setCache(String group, String key, Object value, int seconds) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        boolean flag = false;
        String result = "";
        String combineKey = combineKey(group, key);
        String jsonValue = JSON.toJSONString(value);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                result = jds.setex(combineKey, seconds, jsonValue);
            } else if (shardedJedis != null) {
                result = shardedJedis.setex(combineKey, seconds, jsonValue);
            } else if (jedisCluster != null) {
                result = jedisCluster.setex(combineKey, seconds, jsonValue);
            }
            if ("OK".equalsIgnoreCase(result)) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("设置缓存出错: key={}, value={}", combineKey, jsonValue, e);
        } finally {
            shutdown(jds, shardedJedis);
        }

        return flag;
    }

    @Override
    public boolean setCache(String group, String key, Object value, int seconds, String setParam) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        boolean flag = false;
        String result = "";
        String combineKey = combineKey(group, key);
        String jsonValue = JSON.toJSONString(value);
        SetParams setParams = new SetParams();
        if ("xx".equalsIgnoreCase(setParam)) {
            setParams.xx();
        }else {
            setParams.nx();
        }
        setParams.ex(seconds);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                result = jds.set(combineKey, jsonValue, setParams);
            } else if (shardedJedis != null) {
                result = shardedJedis.set(combineKey, jsonValue, setParams);
            } else if (jedisCluster != null) {
                result = jedisCluster.set(combineKey, jsonValue, setParams);
            }
            if ("OK".equalsIgnoreCase(result)) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("设置缓存出错: key={}, value={}", combineKey, jsonValue, e);
        } finally {
            shutdown(jds, shardedJedis);
        }

        return flag;
    }

    @Override
    public boolean setPersistCache(String group, String key, Object value) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        boolean flag = false;
        String result = "";
        String combineKey = combineKey(group, key);
        String jsonValue = JSON.toJSONString(value);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                jds.expire(combineKey, 1);
                jds.persist(combineKey);
                result = jds.set(combineKey, jsonValue);
            } else if (shardedJedis != null) {
                shardedJedis.expire(combineKey, 1);
                shardedJedis.persist(combineKey);

                result = shardedJedis.set(combineKey, jsonValue);
            } else if (jedisCluster != null) {
                jedisCluster.expire(combineKey, 1);
                jedisCluster.persist(combineKey);

                result = jedisCluster.set(combineKey, jsonValue);
            }
            if ("OK".equalsIgnoreCase(result)) {
                flag = true;
            }
            log.debug("设置持久化缓存: key={}", key);
        } catch (Exception e) {
            log.error("设置缓存出错: key={}, value={}", combineKey, jsonValue, e );
        } finally {
            shutdown(jds, shardedJedis);
        }

        return flag;
    }

    @Override
    public <T> T getCache(String group, String key, Class<T> clz) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        T value = null;
        String json = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                json = jds.get(combineKey);
            } else if (shardedJedis != null) {
                json = shardedJedis.get(combineKey);
            } else if (jedisCluster != null) {
                json = jedisCluster.get(combineKey);
            }
            if (StringUtils.isNotEmpty(json)) {
                value = JSONObject.parseObject(json, clz);
            }
        } catch (Exception e) {
            log.error("读取缓存出错: key={}", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return value;
    }

    @Override
    public boolean delCache(String group, String key) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        boolean flag = false;
        Long del = 0L;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                del = jds.del(combineKey);
            } else if (shardedJedis != null) {
                del = shardedJedis.del(combineKey);
            } else if (jedisCluster != null) {
                del = jedisCluster.del(combineKey);
            }
            if (del > 0L) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            log.error("删除缓存出错: key={}", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return flag;
    }


    @Override
    public boolean add(String key, Object value, int seconds) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        boolean flag = false;
        Long setnx;
        String combineKey = combineKey(key);
        String jsonValue = JSON.toJSONString(value);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                setnx = jds.setnx(combineKey, jsonValue);
                if (setnx == 1L) {
                    jds.expire(combineKey, seconds);
                    flag = true;
                }
            } else if (shardedJedis != null) {
                setnx = shardedJedis.setnx(combineKey, jsonValue);
                if (setnx == 1L) {
                    shardedJedis.expire(combineKey, seconds);
                    flag = true;
                }
            } else if (jedisCluster != null) {
                setnx = jedisCluster.setnx(combineKey, jsonValue);
                if (setnx == 1L) {
                    jedisCluster.expire(combineKey, seconds);
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("新增缓存出错: key={}, value={}", combineKey, jsonValue, e );
        } finally {
            shutdown(jds, shardedJedis);
        }
        return flag;
    }


    @Override
    public Long incr(String key, long initValue) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        Long incr = 0L;
        String combineKey = combineKey(key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                expire((int)initValue, jds, combineKey);
                incr = jds.incr(combineKey);
            } else if (shardedJedis != null) {
                expire((int)initValue, shardedJedis, combineKey);
                incr = shardedJedis.incr(combineKey);
            } else if (this.jedisCluster != null) {
                expire((int)initValue, this.jedisCluster, combineKey);
                incr = this.jedisCluster.incr(combineKey);
            }
            return incr;
        } catch (Exception e) {
            log.error("incr��������: key={}", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return incr;
    }


    @Override
    public Long incrBy(String key, long count, long initValue) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        Long incr = 0L;
        String combineKey = combineKey(key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                expire((int)initValue, jds, combineKey);
                incr = jds.incrBy(combineKey, count);
            } else if (shardedJedis != null) {
                expire((int)initValue, shardedJedis, combineKey);
                incr = shardedJedis.incrBy(combineKey, count);
            } else if (this.jedisCluster != null) {
                expire((int)initValue, this.jedisCluster, combineKey);
                incr = this.jedisCluster.incrBy(combineKey, count);
            }
            return incr;
        } catch (Exception e) {
            log.error("incr��������: key={}", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return incr;
    }


    @Override
    public <T> void lpush(String group, String key, List<T> value, int seconds) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            if (value != null && !value.isEmpty()) {
                jds = getJedis();
                shardedJedis = getShardedJedis();
                if (jds != null) {
                    jds.del(combineKey);
                    String[] obj = getList(value);
                    jds.lpush(combineKey, obj);
                    expire(seconds, jds, combineKey);
                } else if (shardedJedis != null) {
                    shardedJedis.del(combineKey);
                    String[] obj = getList(value);
                    shardedJedis.lpush(combineKey, obj);
                    expire(seconds, shardedJedis, combineKey);
                } else if (this.jedisCluster != null) {
                    this.jedisCluster.del(combineKey);
                    String[] obj = getList(value);
                    this.jedisCluster.lpush(combineKey, obj);
                    expire(seconds, this.jedisCluster, combineKey);
                }
            }
        } catch (Exception e) {
            log.error("REDIS���� ������������: key={}", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
    }


    @Override
    public <T> void rpush(String group, String key, List<T> value, int seconds) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            if (value != null && !value.isEmpty()) {
                jds = getJedis();
                shardedJedis = getShardedJedis();
                if (jds != null) {
                    jds.del(combineKey);
                    String[] obj = getList(value);
                    jds.rpush(combineKey, obj);
                    expire(seconds, jds, combineKey);
                } else if (shardedJedis != null) {
                    shardedJedis.del(combineKey);
                    String[] obj = getList(value);
                    shardedJedis.rpush(combineKey, obj);
                    expire(seconds, shardedJedis, combineKey);
                } else if (this.jedisCluster != null) {
                    this.jedisCluster.del(combineKey);
                    String[] obj = getList(value);
                    this.jedisCluster.rpush(combineKey, obj);
                    expire(seconds, this.jedisCluster, combineKey);
                }
            }
        } catch (Exception e) {
            log.error("REDIS����������������: key={}", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
    }

    private <T> String[] getList(List<T> value) {
        String[] obj = new String[value.size()];
        for (int i = 0; i < value.size(); i++) {
            obj[i] = JSON.toJSONString(value.get(i));
        }
        return obj;
    }


    @Override
    public <T> List<T> getList(String group, String key, Integer from, Integer to, Class<T> clz) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        List<T> tlist = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            List<String> list = null;
            if (jds != null) {
                list = jds.lrange(combineKey, from, to);
            } else if (shardedJedis != null) {
                list = shardedJedis.lrange(combineKey, from, to);
            } else if (this.jedisCluster != null) {
                list = this.jedisCluster.lrange(combineKey, from, to);
            }
            if (!CollectionUtils.isEmpty(list)) {
                tlist = new ArrayList<>();
                for (String str : list) {
                    T t = JSONObject.parseObject(str, clz);
                    tlist.add(t);
                }
            }
            return tlist;
        } finally {
            shutdown(jds, shardedJedis);
        }
    }

    public void shutdown(Jedis jds, ShardedJedis shardedJedis) {
        if (null != jds) {
            jds.close();
        }
        if (null != shardedJedis) {
            shardedJedis.close();
        }
    }


    @Override
    public <T> void hset(String group, String key, String field, T value, int seconds) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        String jsonValue = JSON.toJSONString(value);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                jds.hset(combineKey, field, jsonValue);
                expire(seconds, jds, combineKey);
            } else if (shardedJedis != null) {
                shardedJedis.hset(combineKey, field, jsonValue);
                expire(seconds, shardedJedis, combineKey);
            } else if (this.jedisCluster != null) {
                this.jedisCluster.hset(combineKey, field, jsonValue);
                expire(seconds, this.jedisCluster, combineKey);
            }
        } catch (Exception e) {
            log.error("����map������������: key={}, value={}", combineKey, jsonValue, e );
        } finally {
            shutdown(jds, shardedJedis);
        }
    }


    @Override
    public <T> T hget(String group, String key, String field, Class<T> clz) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String json = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                json = jds.hget(combineKey, field);
            } else if (shardedJedis != null) {
                json = shardedJedis.hget(combineKey, field);
            } else if (this.jedisCluster != null) {
                json = this.jedisCluster.hget(combineKey, field);
            }
            Object object = StringUtils.isNotEmpty(json) ? JSONObject.parseObject(json, clz) : null;
            return (T)object;
        } catch (Exception e) {
            log.error("����map������������: key={}", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public <T> List<T> hget(String group, String key, String field, TypeReference<List<T>> typeReference) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String json = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                json = jds.hget(combineKey, field);
            } else if (shardedJedis != null) {
                json = shardedJedis.hget(combineKey, field);
            } else if (this.jedisCluster != null) {
                json = this.jedisCluster.hget(combineKey, field);
            }
            return StringUtil.isNotBlank(json) ? (List)JSON.parseObject(json, List.class) : null;
        } catch (Exception e) {
            log.error("����map������������: key={}", key, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return new ArrayList<>();
    }


    @Override
    public <K, V> Map<K, V> hgetMap(String group, String key, String field, TypeReference<Map<K, V>> typeReference) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String json = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                json = jds.hget(combineKey, field);
            } else if (shardedJedis != null) {
                json = shardedJedis.hget(combineKey, field);
            } else if (this.jedisCluster != null) {
                json = this.jedisCluster.hget(combineKey, field);
            }
            return StringUtils.isNotEmpty(json) ? JSON.parseObject(json, Map.class) : null;
        } catch (Exception e) {
            log.error("����map������������: key={}", key, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public boolean hdel(String group, String key, String... field) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        long result = 0L;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                result = jds.hdel(combineKey, field);
            } else if (shardedJedis != null) {
                result = shardedJedis.hdel(combineKey, field);
            } else if (this.jedisCluster != null) {
                result = this.jedisCluster.hdel(combineKey, field);
            }
            return (result > 0L);
        } catch (Exception e) {
            log.error("����map������������: key={}", key, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return false;
    }


    @Override
    public boolean expire(String group, String key, int seconds) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        long result = 0L;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                result = jds.expire(combineKey, seconds);
            } else if (shardedJedis != null) {
                result = shardedJedis.expire(combineKey, seconds);
            } else if (this.jedisCluster != null) {
                result = this.jedisCluster.expire(combineKey, seconds);
            }
            return (result > 0L);
        } catch (Exception e) {
            log.error("����������������: key={}", key, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return false;
    }

    private void expire(int seconds, Jedis jds, String combineKey) {
        if (seconds > 0) {
            jds.expire(combineKey, seconds);
        }
    }

    protected void expire(int seconds, ShardedJedis shardedJedis, String combineKey) {
        if (seconds > 0) {
            shardedJedis.expire(combineKey, seconds);
        }
    }

    protected void expire(int seconds, JedisCluster jedisCluster, String combineKey) {
        if (seconds > 0) {
            jedisCluster.expire(combineKey, seconds);
        }
    }


    @Override
    public Set<String> getKeys(String group, String pattern) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, pattern);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                return jds.keys(combineKey);
            }
            if (shardedJedis != null) {
                return getKeys(combineKey, shardedJedis);
            }
            if (this.jedisCluster != null) {
                return getKeys(combineKey);
            }
        } catch (Exception e) {
            log.error("��������������key������: key={}", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return new HashSet<>();
    }


    @Override
    public Long zadd(String group, String key, Map<String, Long> scoreMembers) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            Map<String, Double> map = getMap(scoreMembers);
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                return jds.zadd(combineKey, map);
            }
            if (shardedJedis != null) {
                return shardedJedis.zadd(combineKey, map);
            }
            if (this.jedisCluster != null) {
                return this.jedisCluster.zadd(combineKey, map);
            }
        } catch (Exception e) {
            log.error("������������member��������score��������������key����������: key:{},scoreMembers:{}", combineKey, scoreMembers, e );
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Long zadd(String group, String key, Map<String, Long> scoreMembers, int seconds) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            Map<String, Double> map = getMap(scoreMembers);
            if (jds != null) {
                jds.expire(combineKey, seconds);
                return jds.zadd(combineKey, map);
            }  if (shardedJedis != null) {
                shardedJedis.expire(combineKey, seconds);
                return shardedJedis.zadd(combineKey, map);
            }  if (this.jedisCluster != null) {
                this.jedisCluster.expire(combineKey, seconds);
                return this.jedisCluster.zadd(combineKey, map);
            }
        } catch (Exception e) {
            log.error("������������member��������score��������������key����������: key:{},scoreMembers:{}", combineKey, scoreMembers, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Set<String> zrange(String group, String key, Long start, Long end) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                return jds.zrange(combineKey, start, end);
            }
            if (shardedJedis != null) {
                return shardedJedis.zrange(combineKey, start, end);
            }
            if (this.jedisCluster != null) {
                return this.jedisCluster.zrange(combineKey, start, end);
            }
        } catch (Exception e) {
            log.error("����key����������������������: key:{},start:{},end:{}", combineKey, start, end, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return new HashSet<>();
    }

    @Override
    public Set<String> zrevrange(String group, String key, Long start, Long end) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                return jds.zrevrange(combineKey, start, end);
            }
            if (shardedJedis != null) {
                return shardedJedis.zrevrange(combineKey, start, end);
            }
            if (this.jedisCluster != null) {
                return this.jedisCluster.zrevrange(combineKey, start, end);
            }
        } catch (Exception e) {
            log.error("����key����������������������: key:{},start:{},end:{}", combineKey, start, end, e );
        } finally {
            shutdown(jds, shardedJedis);
        }
        return new HashSet<>();
    }


    @Override
    public Long zrem(String group, String key, List<String> members) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                return jds.zrem(combineKey, members.toArray(new String[0]));
            }
            if (shardedJedis != null) {
                return shardedJedis.zrem(combineKey, members.toArray(new String[0]));
            }
            if (this.jedisCluster != null) {
                return this.jedisCluster.zrem(combineKey, members.toArray(new String[0]));
            }
        } catch (Exception e) {
            log.error("����������key������������������������: key:{},members:{}", combineKey, members, e );
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Long zcard(String group, String key) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                return jds.zcard(combineKey);
            }
            if (shardedJedis != null) {
                return shardedJedis.zcard(combineKey);
            }
            if (this.jedisCluster != null) {
                return this.jedisCluster.zcard(combineKey);
            }
        } catch (Exception e) {
            log.error("����key����������key������������: key:{}", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Long zscore(String group, String key, String members) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            Double scroce = null;
            if (jds != null) {
                scroce = jds.zscore(combineKey, members);
            } else if (shardedJedis != null) {
                scroce = shardedJedis.zscore(combineKey, members);
            } else if (this.jedisCluster != null) {
                scroce = this.jedisCluster.zscore(combineKey, members);
            }
            return (scroce != null) ? Math.round(scroce) : null;
        }
        catch (Exception e) {
            log.error("����key����������key��������member��score��������: key:{},members:{}", combineKey, members, e );
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Long zcount(String group, String key, Long min, Long max) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                return jds.zcount(combineKey, min, max);
            }
            if (shardedJedis != null) {
                return shardedJedis.zcount(combineKey, min, max);
            }
            if (this.jedisCluster != null) {
                return this.jedisCluster.zcount(combineKey, min, max);
            }
        } catch (Exception e) {
            log.error("����key������key����score����min��max����(��������score������min��max)������������: key:{},min:{},max:{}", combineKey, min, max, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Long zcount(String group, String key, String min, String max) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                return jds.zcount(combineKey, min, max);
            }
            if (shardedJedis != null) {
                return shardedJedis.zcount(combineKey, min, max);
            }
            if (this.jedisCluster != null) {
                return this.jedisCluster.zcount(combineKey, min, max);
            }
        } catch (Exception e) {
            log.error("����key������key����score����min��max����(��������score������min��max)������������: key:{},min:{},max:{}", combineKey, min, max, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) { return zscan(getGroup(), key, cursor); }


    @Override
    public ScanResult<Tuple> zscan(String group, String key, String cursor) {
        Jedis jds = threadLocal.get();
        ShardedJedis shardedJedis = shardedThreadLocal.get();
        String combineKey = combineKey(group, key);
        try {
            if (jds == null) {
                jds = getJedis();
            }
            if (shardedJedis == null) {
                shardedJedis = getShardedJedis();
            }
            ScanResult<Tuple> result = null;
            ScanParams params = new ScanParams();
            if (jds != null) {
                threadLocal.set(jds);
                result = jds.zscan(combineKey, cursor, params.count(1000));
            } else if (shardedJedis != null) {
                shardedThreadLocal.set(shardedJedis);
                result = shardedJedis.zscan(combineKey, cursor, params.count(1000));
            } else if (this.jedisCluster != null) {
                result = this.jedisCluster.zscan(combineKey.getBytes(), cursor.getBytes(), params.count(1000));
            }
            if (null != result && "0".equals(result.getCursor())) {
                threadLocal.remove();
                shardedThreadLocal.remove();
            }
            return result;
        } catch (Exception e) {
            log.error("����key:{}, zscan����", combineKey, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Map<Long, String> zrangeWithScores(String group, String key, Long start, Long end) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        Set<Tuple> list = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                list = jds.zrangeWithScores(combineKey, start, end);
            } else if (shardedJedis != null) {
                list = shardedJedis.zrangeWithScores(combineKey, start, end);
            } else if (this.jedisCluster != null) {
                list = this.jedisCluster.zrangeWithScores(combineKey, start, end);
            }
            return getMap(list);
        } catch (Exception e) {
            log.error("����key������������������������key:{},start:{},end:{}", combineKey, start, end, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Map<Long, String> zrevrangeWithScores(String group, String key, Long start, Long end) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        Set<Tuple> list = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                list = jds.zrevrangeWithScores(combineKey, start, end);
            } else if (shardedJedis != null) {
                list = shardedJedis.zrevrangeWithScores(combineKey, start, end);
            } else if (this.jedisCluster != null) {
                list = this.jedisCluster.zrevrangeWithScores(combineKey, start, end);
            }
            return getMap(list);
        } catch (Exception e) {
            log.error("����key������������������������key:{},start:{},end:{}", combineKey, start, end, e);
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Long setnx(String group, String key, String value) {
        Jedis jds = null;
        ShardedJedis shardedJedis = null;
        Long count = null;
        String combineKey = combineKey(group, key);
        try {
            jds = getJedis();
            shardedJedis = getShardedJedis();
            if (jds != null) {
                count = jds.setnx(combineKey, value);
            } else if (shardedJedis != null) {
                count = shardedJedis.setnx(combineKey, value);
            } else if (jedisCluster != null) {
                count = jedisCluster.setnx(combineKey, value);
            }
            return count;
        } catch (Exception e) {
            log.error("����key SETNX��������key:{},value:{}", combineKey, value, e );
        } finally {
            shutdown(jds, shardedJedis);
        }
        return null;
    }


    @Override
    public Transaction multi() {
        Jedis jds = threadLocal.get();
        if (jds == null) {
            jds = getJedis();
            if (jds != null) {
                threadLocal.set(jds);
            }
        }
        Transaction transaction = null;
        try {
            if (jds != null) {
                transaction = jds.multi();
            }
            return transaction;
        } catch (Exception e) {
            log.error("������������", e);

            return null;
        }
    }


    @Override
    public void shutdown() {
        Jedis jds = threadLocal.get();
        shutdown(jds, null);
        threadLocal.remove();
    }


    @Override
    public String watch(String... key) {
        Jedis jds = threadLocal.get();
        if (jds == null) {
            try {
                jds = getJedis();
                if (jds != null) {
                    threadLocal.set(jds);
                    return jds.watch(key);
                }
                log.error("��������redis����");
                return null;
            }
            catch (Exception e) {
                log.error("watch����", e);
                return null;
            }
        }
        return jds.watch(key);
    }



    @Override
    public void unwatch() {
        Jedis jds = threadLocal.get();
        if (jds != null) {
            jds.unwatch();
        }
    }

    private Map<String, Double> getMap(Map<String, Long> scoreMembers) {
        Map<String, Double> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(scoreMembers)) {
            for (Map.Entry<String, Long> m : scoreMembers.entrySet()) {
                map.put(m.getKey(), Double.valueOf((m.getValue())));
            }
        }
        return map;
    }

    private static Map<Long, String> getMap(Set<Tuple> list) {
        LinkedHashMap<Long, String> map = new LinkedHashMap<>();
        for (Tuple tuple : list) {
            map.put(Math.round(tuple.getScore()), tuple.getElement());
        }
        return map;
    }

    public Set<String> getKeys(String pattern, ShardedJedis shardedJedis) {
        Set<String> list = new HashSet<>();
        Collection<Jedis> allShards = shardedJedis.getAllShards();
        for (Jedis jedis : allShards) {
            Set<String> keys = jedis.keys(pattern);
            list.addAll(keys);
        }
        return list;
    }


    @Override
    public Set<String> getKeys(String pattern) {
        log.debug("Start getting keys...");
        Set<String> keys = new HashSet<>();
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for (String k : clusterNodes.keySet()) {
            log.debug("Getting keys from: {}", k);
            JedisPool jp = clusterNodes.get(k);
            Jedis connection = jp.getResource();
            try {
                keys.addAll(connection.keys(pattern));
            } catch (Exception e) {
                log.error("Getting keys error.", e);
            } finally {
                log.debug("Connection closed.");
                connection.close();
            }
        }
        log.debug("Keys gotten!");
        return keys;
    }
}

