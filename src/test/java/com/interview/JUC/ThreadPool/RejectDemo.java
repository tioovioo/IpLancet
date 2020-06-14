package com.interview.JUC.ThreadPool;

import java.util.concurrent.*;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/19.
 * <p>
 * 线程池拒绝策略:
 * <p>
 * 定义:
 * 等待队列也已经排满了，再也塞不下新任务了，
 * 同时线程池中的max线程也达到了，无法继续为新任务服务。
 * 这时我们就需要拒绝策略机制合理的处理这个问题。
 * JDK内置的4种拒绝策略:
 * 1).AbortPolicy(默认)
 * 直接抛出RejectedExecutionException异常阻止系统正常运行
 * 2).CallerRunsPolicy
 * ”调用者运行“一种调节机制，该策略既不会抛弃任务，也不会抛出异常，
 * 而是将某些任务回退到调用者，从而降低新任务的流量
 * 3).DiscardOldestPolicy
 * 抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
 * 4).DiscardPolicy
 * 直接丢弃任务，不予任何处理也不抛异常。如果允许任务丢失，这是最好的一种方案
 * <p>
 * 特点:
 * 均实现了RejectedExecutionHandler接口.
 */
public class RejectDemo {

    public static void main(String[] args) {
        System.err.println("当前主机的线程数:\t"+Runtime.getRuntime().availableProcessors());

        /**
         *  一般工作中,使用自定义实现线程池.
         */
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5,
                2L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        /**
         * 演示4中拒绝策略的情况.
         * ThreadPoolExecutor.AbortPolicy() -> java.util.concurrent.RejectedExecutionException;
         * ThreadPoolExecutor.CallerRunsPolicy()->回退到调用者 main;
         * ThreadPoolExecutor.DiscardOldestPolicy()->抛弃队列中等待最久的任务;
         * ThreadPoolExecutor.DiscardPolicy()-> 直接丢弃任务，不予任何处理也不抛异常.
         *
         * 自定义线程池,如何合理配置线程池的参数Max?
         * 1)->CPU密集型: 例如:while(true){}
         *      需要大量的运算，而没有阻塞，CPU一直全速运行
         *      CPU密集型任务配置尽可能少的线程数量：
         *     一般公式：-> CPU核数+1个线程的线程池
         * 2)-> IO密集型: 例如:循环从数据库中取值.
         *      a. IO密集型任务线程并不是一直在执行任务，
         *          则应配置经可能多的线程，如-> CPU核数 * 2;
         *      b. 该任务需要大量的IO，即大量的阻塞,
         *          在单线程上运行IO密集型的任务会导致浪费大量的 CPU运算能力浪费在等待。
         *          所以在IO密集型任务中使用多线程可以大大的加速程序运行，即使在单核CPU上，
         *          这种加速主要就是利用了被浪费掉的阻塞时间。
         *          IO密集型时，大部分线程都阻塞，故需要多配置线程数：
         *          参考公式：==CPU核数/（1-阻塞系数） 阻塞系数在0.8~0.9之间==
         *          八核CPU：8/（1-0，9）=80
         */

        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t执行任务...");
                });
            }
        } finally {
            threadPool.shutdown();
        }

    }
}
