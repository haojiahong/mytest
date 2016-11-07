package com.hao.util.lock;

import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Created by haojiahong on 16/11/4.
 */
@Service
public class RedisLockFactory {
    private static final String REDIS_LOCK_KEY_PRE = "mytest_redis_lock_";

    @Resource
    private JedisPool jedisPool;

    public RedisLock newRedisLock(String lockKey) {
        return new RedisLock(REDIS_LOCK_KEY_PRE + lockKey, jedisPool);
    }
}
