package com.hao.util.lock;

import com.hao.util.JedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

/**
 * Created by haojiahong on 16/11/4.
 */
public class RedisLock {

    private static Logger log = LoggerFactory.getLogger(RedisLock.class);
    private static final String LOCKED = "TRUE";
    public static final long ONE_MILLI_NANOS = 1000000L;
    //锁的超时时间（秒），过期删除
    public static final int EXPIRE = 300;
    //默认超时时间（毫秒）
    public static final long DEFAULT_TIME_OUT = 15 * 1000;

    private final Random r = new Random();
    private Jedis jedis;
    private String key;
    //锁状态标志
    private boolean locked = false;

    private JedisPool pool;


    public RedisLock(String key, JedisPool pool) {
        this.pool = pool;
        this.key = key;
        this.jedis = pool.getResource();
        this.jedis.select(JedisClient.DEFAULT_DB_INDEX);
    }

    public boolean lock(long timeout) throws InterruptedException {
        long nano = System.nanoTime();
        timeout *= ONE_MILLI_NANOS;
        while ((System.nanoTime() - nano) < timeout) {
            if (jedis.setnx(key, LOCKED) == 1) {
                jedis.expire(key, EXPIRE);
                locked = true;
                log.info("当前请求成功获取到锁，key=" + key);
                return locked;
            }
            // 短暂休眠，nano避免出现活锁
            Thread.sleep(3, r.nextInt(500));
        }
        return false;
    }

    public boolean lock() throws InterruptedException {
        return lock(DEFAULT_TIME_OUT);
    }

    // 无论是否加锁成功，必须调用
    public void unlock() {
        try {
            if (locked)
                jedis.del(key);
        } finally {
            pool.returnResource(jedis);
        }
    }


}
