package com.hao.mytest.test;

import com.hao.seckill.CacheLockInterceptor;
import com.hao.seckill.SecKillService;
import com.hao.seckill.impl.SecKillServiceImpl;
import com.hao.util.SpringIocUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Proxy;

/**
 * 秒杀测试类
 * Created by haojiahong on 16/11/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-applicaton-context.xml")
public class SecKillTest {

    @Autowired
    private SecKillService secKillService;

    private static Long commidityId1 = 10000001L;
    private static Long commidityId2 = 10000002L;


    @Test
    public void secKillTest() {
        int threadCount = 1000;
        int splitPoint = 500;
        final SecKillServiceImpl secKillServiceImpl = new SecKillServiceImpl();


        Thread[] threads = new Thread[threadCount];
        //起500个线程，秒杀第一个商品
        for (int i = 0; i < splitPoint; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    //用动态代理的方式调用secKill方法
                    SecKillService proxy = (SecKillService) Proxy.newProxyInstance(SecKillService.class.getClassLoader(),
                            new Class[]{SecKillService.class}, new CacheLockInterceptor(secKillServiceImpl));
                    proxy.secKill("test", commidityId1);
                }
            });
            threads[i].start();
        }
        long startTime = System.currentTimeMillis();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(SecKillServiceImpl.inventory.get(commidityId1));
        System.out.println("total cost " + (System.currentTimeMillis() - startTime));

    }

    @Test
    public void beanTest() {
        System.out.println(secKillService);
        System.out.println(1111);
//        JedisPool jedisPool = (JedisPool) SpringIocUtil.getBean("jedisPool");
//        System.out.println(jedisPool);
    }
}
