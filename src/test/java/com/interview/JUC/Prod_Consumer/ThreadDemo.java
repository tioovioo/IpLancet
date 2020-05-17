package com.interview.JUC.Prod_Consumer;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/17.
 * <p>
 * <p>
 * 线程的几种状态:
 * 1.新建状态(New): 当用new操作符创建一个线程时, 例如new Thread(r),...
 * 2.就绪状态(Runnable) 一个新创建的线程并不自动开始运行,要执行线程,必须调用线程...
 * 3.运行状态(Running) 当线程获得CPU时间后,它才进入运行状态,真正开始执行run...
 * 4.阻塞状态(Blocked) 线程运行过程中,可能由于各种原因进入阻塞状态
 * 5.死亡状态(Dead) 有两个原因会导致线程死亡: 1) run方法正常退出而自然死亡.
 */
public enum ThreadDemo {
    //线程刚创建
    NEW,

    //在JVM中正在运行的线程
    RUNNABLE,

    //线程处于阻塞状态，等待监视锁，可以重新进行同步代码块中执行
    BLOCKED,

    //等待状态
    WAITING,

    //调用sleep() join() wait()方法可能导致线程处于等待状态
    TIMED_WAITING,

    //线程执行完毕，已经退出
    TERMINATED;
}