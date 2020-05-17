package com.lancet.iplancet.multithread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yongjia.guo
 */
public class ThreadTest {


    @Test
    public void cachThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Runtime.getRuntime().availableProcessors());
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程:==>  " + Thread.currentThread().getName());
                }
            });
        }
    }

    @Test
    public void taskThreadPool() {
        //创建一个定长线程池，支持定时及周期性任务执行——延迟执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        scheduledThreadPool.schedule(new Runnable() {
                                         @Override
                                         public void run() {
                                             System.out.println("当前线程: " + Thread.currentThread().getName() + "===延迟一秒执行=======");
                                         }
                                     }

                , 3, TimeUnit.SECONDS);


    }


    public static void main(String[] args) {

        //创建一个定长线程池，支持定时及周期性任务执行——延迟执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
//        scheduledThreadPool.schedule(()->
//                System.out.println("当前线程: " + Thread.currentThread().getName() + "===延迟一秒执行=======")
//                , 3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(()->{
                  try {
                      System.out.println(Thread.currentThread().getName()+"定时任务---");
                      Thread.sleep(1000);
                  }catch (Exception e){
                      e.printStackTrace();
                  }
                }
        ,0,3,TimeUnit.SECONDS);

    }
}
