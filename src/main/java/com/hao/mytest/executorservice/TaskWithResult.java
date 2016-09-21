package com.hao.mytest.executorservice;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by haojiahong on 16/4/20.
 */
public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }


    public String call() throws Exception {
        System.out.println("call方法被自动调用，干活。。" + Thread.currentThread().getName());
        for (int i = 99999999; i > 0; i--) ;//模拟耗时操作

        if (new Random().nextBoolean()) {
            throw new MyTaskException("meet error in task " + Thread.currentThread().getName());
        }
        return Thread.currentThread().getName() + "call 方法返回的任务结果是" + id;
    }

    class MyTaskException extends Exception {
        public MyTaskException(String message) {
            super(message);
        }
    }
}
