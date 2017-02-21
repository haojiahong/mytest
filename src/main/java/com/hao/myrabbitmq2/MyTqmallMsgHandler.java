package com.hao.myrabbitmq2;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by haojiahong on 17/2/20.
 */
@Slf4j
public class MyTqmallMsgHandler {

    public void handleMessage(Object obj) {
        if (obj instanceof String) {
            log.info("##=======start process local message:" + obj);
            try {
                String aa = JSON.toJSONString(obj);
                System.out.println(aa);
            } catch (Exception e) {
                log.error("process error" + obj.toString(), e);
            }
        } else {
            log.error("not process", obj);
        }
    }
}
