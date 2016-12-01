package com.hao.mytest.test;

import com.hao.seckill.CacheLockInterceptor;
import com.hao.seckill.SecKillService;
import com.hao.seckill.impl.SecKillServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        int splitPoint = 1;
        final SecKillServiceImpl secKillServiceImpl = new SecKillServiceImpl();


        Thread[] threads = new Thread[3];
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
        System.out.println("haojiahong---------" + SecKillServiceImpl.inventory.get(commidityId1));
        System.out.println("total cost " + (System.currentTimeMillis() - startTime));

    }

    @Test
    public void singleSecKillTest() {
        SecKillService proxy = new CacheLockInterceptor(new SecKillServiceImpl()).getProxy();
        proxy.secKill("test", commidityId1);
        System.out.println("haojiahong---------" + SecKillServiceImpl.inventory.get(commidityId1));
    }

    @Test
    public void someSecKillTest() {
        Thread[] threads = new Thread[500];
        for (int i = 0; i < 500; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    SecKillService proxy = new CacheLockInterceptor(new SecKillServiceImpl()).getProxy();
                    proxy.secKill("test", commidityId1);
                }
            });
            threads[i].start();
        }
        long startTime = System.currentTimeMillis();
        for (Thread sonThread : threads) {
            try {
                sonThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("haojiahong---------" + (10000 - SecKillServiceImpl.inventory.get(commidityId1)));
        System.out.println("haojiahong---------" + CacheLockInterceptor.ERROR_COUNT);
        System.out.println("haojiahong-----total:" + (CacheLockInterceptor.ERROR_COUNT + SecKillServiceImpl.inventory.get(commidityId1)));
        System.out.println("total cost " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void beanTest() {
        System.out.println(secKillService);
        System.out.println(1111);
//        JedisPool jedisPool = (JedisPool) SpringIocUtil.getBean("jedisPool");
//        System.out.println(jedisPool);
    }

    @Test
    public void testNanao() {
        long timeout = 15 * 1000;
        long nano = System.nanoTime();
        while (System.nanoTime() - nano < timeout) {
            System.out.println("进来了：" + (System.nanoTime() - nano));
            System.out.println(System.nanoTime());
        }
        System.out.println(System.nanoTime() - nano);

    }
}
