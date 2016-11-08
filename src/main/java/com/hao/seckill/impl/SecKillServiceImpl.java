package com.hao.seckill.impl;

import com.hao.seckill.LockedObject;
import com.hao.seckill.SecKillService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haojiahong on 16/11/7.
 */
@Service
public class SecKillServiceImpl implements SecKillService {
    public static Map<Long, Long> inventory;

    static {
        inventory = new HashMap<>();
        inventory.put(10000001L, 10000l);
        inventory.put(10000002L, 10000l);
    }

    @Override
    public void secKill(String arg1, Long arg2) {
        //最简单的秒杀，这里仅作为demo示例
        reduceInventory(arg2);
    }

    //模拟秒杀操作，姑且认为一个秒杀就是将库存减一，实际情景要复杂的多
    public Long reduceInventory(Long commodityId) {
        inventory.put(commodityId, inventory.get(commodityId) - 1);
        return inventory.get(commodityId);
    }
}
