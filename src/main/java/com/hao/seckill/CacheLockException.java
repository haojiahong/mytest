package com.hao.seckill;

/**
 * Created by haojiahong on 16/11/3.
 */
public class CacheLockException extends RuntimeException {
    public CacheLockException() {

    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CacheLockException(String msg) {
        this.msg = msg;
    }
}
