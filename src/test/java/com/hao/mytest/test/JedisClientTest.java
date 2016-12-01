package com.hao.mytest.test;

import com.hao.util.CacheTime;
import com.hao.util.JedisClient;
import com.hao.util.lock.RedisLock;
import com.hao.util.lock.RedisLockFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by haojiahong on 16/11/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-applicaton-context.xml")
public class JedisClientTest {
    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private RedisLockFactory redisLockFactory;

    @Test
    public void testJedis() {
        jedisClient.set("hao_test_aaa", CacheTime.CACHE_EXP_TEN_MINUTES, 22);
        Integer result = jedisClient.get("hao_test_aaa", Integer.class);
        System.out.println(result);

    }

    @Test
    public void testJedisFactory() throws Exception {
        RedisLock lock = redisLockFactory.newRedisLock("test_hao_redis");
        lock.lock();
        System.out.println(222);

    }
}
