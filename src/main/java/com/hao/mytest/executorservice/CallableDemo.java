package com.hao.mytest.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by haojiahong on 16/4/20.
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<Future<String>>();

        //创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            Future<String> future = executorService.submit(new TaskWithResult(i));
            futureList.add(future);

        }

        //遍历任务的结果
        for (Future<String> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
//                executorService.shutdownNow();//会停止以后的所有任务。
                executorService.shutdown();//不会停止添加了但是在等待中的任务。
            } catch (ExecutionException e) {

                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
    }
}
