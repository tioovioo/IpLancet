package com.interview.JUC.ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/18.
 * <p>
 * 创建线程的几种个方法:
 * 1.继承Thread类;
 * 2.实现Runnable接口;
 * 3.实现Callable接口;
 * 4.java.util.concurrent.Executors 线程池调用.
 */

//Integer 为返回值类型.
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.err.println(Thread.currentThread().getName() + "\tcome in..");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws Exception {
        MyThread myThread = new MyThread();

        /**
         *  class FutureTask<V> implements RunnableFuture<V>
         *  interface RunnableFuture<V> extends Runnable, Future<V>
         *  public Thread(Runnable target)
         */

        FutureTask<Integer> futureTask = new FutureTask<Integer>(myThread);

        new Thread(futureTask).start();
        //执行完成才能跳出循环.
//        while (!futureTask.isDone()){
//
//        }

        System.out.println("返回值:\t" + futureTask.get());

    }
}
