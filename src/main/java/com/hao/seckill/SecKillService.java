package com.hao.seckill;

/**
 * Created by haojiahong on 16/11/7.
 */
public interface SecKillService {
    @CacheLock(lockedPrefix = "TEST_PREFIX")
    public void secKill(String arg1, @LockedObject Long arg2);
}
