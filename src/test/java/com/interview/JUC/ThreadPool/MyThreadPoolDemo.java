package com.interview.JUC.ThreadPool;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/18.
 * <p>
 * 线程池Demo.
 * 第四种创建多线程的方法> 线程池创建.
 * <p>
 * 优点:
 * 线程池做的工作主要是控制运行的线程的数量，处理过程中将任务放入队列，
 * 然后在线程创建后启动给这些任务，如果线程数量超过了最大数量，
 * 超出数量的线程排队等候，等其他线程执行完毕，再从队列中取出任务来执行
 * <p>
 * 主要特点:
 * 线程复用、控制最大并发数、管理线程
 * <p>
 * 降低资源消耗，通过重复利用已创建的线程降低线程创建和销毁造成的消耗
 * 提过响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行
 * 提高线程的客观理想。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，
 * 还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控
 * <p>
 * 类-Executors
 * 类-ScheduledThreadPoolExecutor-->类-ThreadPoolExecutor
 * 类-ThreadPoolExecutor-->类-AbstractExecutorService
 * 类-AbstractExecutorService-.->接口-ExecutorService
 * 类-ScheduledThreadPoolExecutor-.->接口-ScheduledExecutorService
 * 接口-ScheduledExecutorService-->接口-ExecutorService
 * 接口-ExecutorService-->接口-Executor
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        //查看可用的处理器数量:
//        System.out.println(Runtime.getRuntime().availableProcessors()); //4

        /**
         * 实现有五种:(常用3种)
         *   newFixedThreadPool()->执行长期的任务，性能好很多(*)
         *   newSingleThreadExecutor()->一个任务一个任务执行的场景(*)
         *   newCachedThreadPool()->执行很多短期异步的小程序或负载较轻的服务器(*)
         *
         *   Executors.newScheduledThreadPool()是带时间调度的，
         *   java8新推出Executors.newWorkStealingPool(int),使用目前机器上可用的处理器作为他的并行级别
         *
         *   execute(Runnable command)-> 不需要返回值
         *   submit(Callable<T> task)-> 需要返回值.
         */

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        try {
            //模拟10个线程执行任务.
            for (int i = 1; i <= 10; i++) {
                threadPool3.execute(
                        () -> {
                            System.out.println(Thread.currentThread().getName() + "\t执行任务..");
                        });
            }
        } finally {
            //线程池使用完成后,需要关闭.
            threadPool3.shutdown();
        }
    }

    /**
     * 底层源码:
     *  newFixedThreadPool()->
     *      return new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>());
     *  newSingleThreadExecutor()->
     *      return new FinalizableDelegatedExecutorService
     *             (new ThreadPoolExecutor(1, 1,
     *                                     0L, TimeUnit.MILLISECONDS,
     *                                     new LinkedBlockingQueue<Runnable>()));
     *  newCachedThreadPool()->
     *      return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>());
     */



}
