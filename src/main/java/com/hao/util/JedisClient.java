package com.hao.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.SafeEncoder;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.List;

/**
 * Created by haojiahong on 16/11/4.
 */
@Slf4j
public class JedisClient {

    /**
     * 缓存时效 1分钟
     */
    public static int CACHE_EXP_MINUTE = 60;

    /**
     * 缓存时效 10分钟
     */
    public static int CACHE_EXP_MINUTES = 60 * 10;

    /**
     * 缓存时效 60分钟
     */
    public static int CACHE_EXP_HOURS = 60 * 60;

    /**
     * 缓存时效 1天
     */
    public static int CACHE_EXP_DAY = 3600 * 24;

    /**
     * 缓存时效 1周
     */
    public static int CACHE_EXP_WEEK = 3600 * 24 * 7;

    /**
     * 缓存时效 1月
     */
    public static int CACHE_EXP_MONTH = 3600 * 24 * 30 * 7;

    /**
     * 自己测试公司的redis使用4
     */
    public static int DEFAULT_DB_INDEX = 3;
    /**
     * 缓存时效 6:小时
     */
    public static int CACHE_EXP_QUARTER_DAY = 6 * 60 * 60;

    private static final List<Class<?>> SIMPLE_CLASS_OBJ = Lists.newArrayList();

    static {
        SIMPLE_CLASS_OBJ.add(Number.class);
        SIMPLE_CLASS_OBJ.add(String.class);
        SIMPLE_CLASS_OBJ.add(Boolean.class);
    }

    @Resource
    private JedisPool jedisPool;

    /**
     * 从redis默认db dbIndex＝DEFAULT_DB_INDEX 设置数据
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public boolean set(final String key, final int seconds, final Object value) {
        return set(DEFAULT_DB_INDEX, key, seconds, value);
    }

    /**
     * 从redis指定db dbIndex 设置数据
     *
     * @param dbIndex
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public boolean set(final int dbIndex, final String key, final int seconds, final Object value) {
        if (key == null || value == null) {
            return false;
        }
        Object ret = runTask(new Callback() {
            @Override
            public Object onTask(Jedis jedis) {
                jedis.select(dbIndex);
                String obj = null;
                if (isSimpleObj(value.getClass())) {
                    if (seconds == 0) {
                        obj = jedis.set(key, value.toString());
                    } else {
                        obj = jedis.setex(key, seconds, value.toString());
                    }
                    return obj;
                }

                byte[] bKey = SafeEncoder.encode(key);
                byte[] bValue = serialize(value);

                if (seconds == 0) {
                    obj = jedis.set(bKey, bValue);
                } else {
                    obj = jedis.setex(bKey, seconds, bValue);
                }
                return obj;
            }
        });
        return ret != null && "OK".equals(ret);
    }

    public <T> T get(final String key, final Class<T> cls) {
        return get(DEFAULT_DB_INDEX, key, cls);
    }

    /**
     * 从redis指定db dbIndex 获取数据
     *
     * @param dbIndex db index
     * @param key
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(final int dbIndex, final String key, final Class<T> cls) {
        Object ret = runTask(new Callback() {
            @Override
            public Object onTask(Jedis jedis) {
                jedis.select(dbIndex);
                Object obj = null;
                if (isSimpleObj(cls)) {
                    String str = jedis.get(key);
                    if (str != null)
                        obj = createSimpleObj(str, cls);
                } else {
                    byte[] bs = jedis.get(SafeEncoder.encode(key));
                    if (bs != null) {
                        obj = deserialize(bs);
                    }
                }
                return obj;
            }
        });
        return ret == null ? null : (T) ret;
    }

    public Boolean del(final int dbIndex, final String key) {
        Object ret = runTask(new Callback() {
            @Override
            public Object onTask(Jedis jedis) {
                jedis.select(dbIndex);
                return jedis.del(key);
            }
        });
        return ret != null && (Long) ret != 0;
    }

    public long incr(final int dbIndex, final String key) {
        Object ret = runTask(new Callback() {
            @Override
            public Object onTask(Jedis jedis) {
                jedis.select(dbIndex);
                return jedis.incr(key);
            }
        });
        return ret == null ? 0l : (Long) ret;
    }

    public Long expire(final int dbIndex, final String key, final int seconds) {
        Object ret = runTask(new Callback() {
            @Override
            public Object onTask(Jedis jedis) {
                jedis.select(dbIndex);
                return jedis.expire(key, seconds);
            }
        });
        return ret == null ? 0l : (Long) ret;
    }

    /**
     * 从默认redis DEFAULT_DB_INDEX  获取list所有元素
     */
    public <T> List<T> getListRange(final String key) {
        return getListRange(DEFAULT_DB_INDEX, key);
    }

    /**
     * 从redis 指定的db dbIndex 获取list所有元素
     *
     * @param dbIndex db
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListRange(final int dbIndex, final String key) {
        Object ret = runTask(new Callback() {
            @Override
            public Object onTask(Jedis jedis) {
                jedis.select(dbIndex);
                return jedis.lrange(key, 0, jedis.llen(key) - 1);
            }
        });
        return ret == null ? Collections.<T>emptyList() : (List<T>) ret;
    }

    @SuppressWarnings("unchecked")
    private <T> T createSimpleObj(String arg, Class<T> cls) {
        T ret = null;
        Constructor<?>[] constructors = cls.getDeclaredConstructors();
        for (Constructor<?> t : constructors) {
            Class<?>[] parameterTypes = t.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(String.class)) {
                try {
                    ret = (T) t.newInstance(arg);
                } catch (Exception e) {
                    log.error("create simple obj error: ", e);
                }
                break;
            }
        }
        return ret;
    }

    private Object deserialize(byte[] bytes) {
        ByteArrayInputStream byteAIS = new ByteArrayInputStream(bytes);
        ObjectInputStream objIS = null;
        try {
            objIS = new ObjectInputStream(byteAIS);
            return objIS.readObject();
        } catch (Exception e) {
            log.error("deserialize error: " + e.getMessage());
        } finally {
            try {
                byteAIS.close();
                if (objIS != null) objIS.close();
            } catch (IOException e) {
                log.error("deserialize close error: ", e);
            }
        }
        return null;
    }

    private byte[] serialize(Object object) {
        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException("设定缓存的对象：" + object.getClass() + "无法序列化，确保 implements Serializable");
        }
        ObjectOutputStream objOS = null;
        ByteArrayOutputStream byteAOS = new ByteArrayOutputStream();
        try {
            objOS = new ObjectOutputStream(byteAOS);
            objOS.writeObject(object);
            return byteAOS.toByteArray();
        } catch (Exception e) {
            log.error("serialize error: " + e.getMessage());
        } finally {
            try {
                if (objOS != null) objOS.close();
                byteAOS.close();
            } catch (IOException e) {
                log.error("serialize close error : ", e);
            }
        }
        return null;
    }

    private static boolean isSimpleObj(Class<?> classObj) {
        for (Class<?> c : SIMPLE_CLASS_OBJ) {
            if (c.isAssignableFrom(classObj))
                return true;
        }
        return false;
    }

    private Object runTask(Callback callback) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = jedisPool.getResource();
            return callback.onTask(jedis);
        } catch (JedisException e) {
            broken = handleJedisException(e);
        } catch (Exception e) {
            log.error("Redis runTask error: ", e);
        } finally {
            closeResource(jedis, broken);
            jedis = null;
        }
        return null;
    }

    interface Callback {
        Object onTask(Jedis jedis);
    }

    /**
     * Handle jedisException, write log and return whether the connection is broken.
     */
    private boolean handleJedisException(JedisException jedisException) {
        if (jedisException instanceof JedisConnectionException) {
            log.error("Redis connection lost.", jedisException);
        } else if (jedisException instanceof JedisDataException) {
            if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
                log.error("Redis connection are read-only slave.", jedisException);
            } else {
                // dataException, isBroken=false
                return false;
            }
        } else {
            log.error("Jedis exception happen.", jedisException);
        }
        return true;
    }

    /**
     * Return jedis connection to the pool, call different return methods depends on the conectionBroken status.
     */
    private void closeResource(Jedis jedis, boolean conectionBroken) {
        try {
            if (conectionBroken) {
                jedisPool.returnBrokenResource(jedis);
            } else {
                jedisPool.returnResource(jedis);
            }
        } catch (Exception e) {
            log.error("return back jedis failed, will fore close the  jedis.", e);
            jedis.close();
        }
    }

    /**
     * 设定一个hash对象
     *
     * @param key   哈希表中的key
     * @param field 域
     * @param value 值
     * @return 如果是第一次创建，则返回true，否则为false
     */
    public boolean setHash(final String key, final int seconds, final String field, final Object value) {
        return setHash(DEFAULT_DB_INDEX, key, seconds, field, value);
    }

    public boolean setHash(final int dbIndex, final String key, final int seconds, final String field, final Object
            value) {
        if (key == null || field == null || value == null) {
            return false;
        }
        Object succeed = runTask(new Callback() {
            @Override
            public Object onTask(Jedis jedis) {
                jedis.select(dbIndex);
                Long ret;
                if (isSimpleObj(value.getClass())) {
                    ret = jedis.hset(key, field, value.toString());
                } else {
                    byte[] bKey = SafeEncoder.encode(key);
                    byte[] bField = SafeEncoder.encode(field);
                    byte[] bValue = serialize(value);
                    ret = jedis.hset(bKey, bField, bValue);
                }
                if (seconds != 0) {//CACHE_EXP_FOREVER=0
                    jedis.expire(key, seconds);
                }
                return ret != null && ret == 1;
            }
        });
        return succeed != null && (Boolean) succeed;
    }

    public <T> T getHash(final String key, final String field, final Class<T> cls) {
        return getHash(DEFAULT_DB_INDEX, key, field, cls);
    }

    @SuppressWarnings("unchecked")
    public <T> T getHash(final int dbIndex, final String key, final String field, final Class<T> cls) {
        if (field == null)
            throw new IllegalArgumentException("field can not null");
        Object ret = runTask(new Callback() {
            @Override
            public Object onTask(Jedis jedis) {
                jedis.select(dbIndex);
                Object obj = null;
                if (isSimpleObj(cls)) {
                    String str = jedis.hget(key, field);
                    if (str != null)
                        obj = createSimpleObj(str, cls);
                } else {
                    byte[] bs = jedis.hget(SafeEncoder.encode(key), SafeEncoder.encode(field));
                    if (bs != null) {
                        obj = deserialize(bs);
                    }
                }
                return obj;
            }
        });
        return ret == null ? null : (T) ret;
    }

    public <T> List<T> getHash(final String key, final Class<T> cls, final String... fields) {
        return getHash(DEFAULT_DB_INDEX, key, cls, fields);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getHash(final int dbIndex, final String key, final Class<T> cls, final String... fields) {
        Object ret = runTask(new Callback() {
            @Override
            public Object onTask(Jedis jedis) {
                jedis.select(dbIndex);
                final byte[][] bfields = new byte[fields.length][];
                for (int i = 0; i < bfields.length; i++) {
                    bfields[i] = SafeEncoder.encode(fields[i]);
                }
                List<byte[]> bytes = jedis.hmget(SafeEncoder.encode(key), bfields);
                if (bytes == null) return null;
                List<T> retList = Lists.newArrayList();
                boolean isSimple = isSimpleObj(cls);
                for (byte[] e : bytes) {
                    if (e == null) {
                        retList.add(null);
                        continue;
                    }
                    retList.add(isSimple ? createSimpleObj(SafeEncoder.encode(e), cls) : (T) deserialize(e));
                }
                return retList;
            }
        });
        return ret == null ? null : (List<T>) ret;
    }
}
