package com.interview.JUC.Lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/15.
 * <p>
 * 锁:
 * 公平锁/非公平锁
 * 非公平锁代表: ReentrantLock 和 synchronized.(可重入锁)
 * <p>
 * 可重入锁(递归锁):
 * 定义:
 * 指的时同一线程外层函数获得锁之后，
 * 内层递归函数仍然能获取该锁的代码，在同一个线程在外层方法获取锁的时候，
 * 在进入内层方法会自动获取锁，也就是说，
 * ==线程可以进入任何一个它已经拥有的锁所同步着的代码块==
 * 用途:
 * 最大的作用是避免死锁.
 */


//验证synchronized是可重入锁.
class Phone {
    public synchronized void sendMessage() throws Exception {
        System.err.println(Thread.currentThread().getName() + "\t invoke sendMessage!");
        sendMail();
    }

    public synchronized void sendMail() throws Exception {
        System.err.println(Thread.currentThread().getName() + "\t invoke sendMail!");
    }
}

//验证ReentrantLock是可重入锁.
class Book implements Runnable {


    @Override
    public void run() {
        get();

    }

    //创建锁对象:(默认非公平)
    Lock reentrantLock = new ReentrantLock();

    public void get() {
        /**
         * 此处可以加多把锁,但是作用和一把锁相同.
         * 加锁和就必须要有对应的解锁,否则会出现程序卡死.
         */
        //加锁
        reentrantLock.lock();
        reentrantLock.lock();
        System.err.println(Thread.currentThread().getName() + "\t invoke get!");
        try {
            //验证可重入锁:
            //线程可以进入任何一个它已经拥有的锁所同步着的代码块.
            set();
        } finally {
            //解锁
            reentrantLock.unlock();
            reentrantLock.unlock();
        }
    }

    public void set() {
        //加锁
        reentrantLock.lock();
        try {
            System.err.println(Thread.currentThread().getName() + "\t invoke set!");
        } finally {
            //解锁
            reentrantLock.unlock();
        }
    }
}

public class ReentrantLockDemo {

    public static void main(String[] args) {
//        ReentrantLock reentrantLock = new ReentrantLock(true);

        //验证synchronized可重入锁.
        synchronizedCheckMethod();

        try {
            //main线程暂停1秒, 等待上述操作执行完成.
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //验证ReentrantLock是可重入锁.
        Book book = new Book();
        Thread t3 = new Thread(book, "T3");
        Thread t4 = new Thread(book, "T4");
        t3.start();
        t4.start();
    }

    /**
     * 验证synchronized可重入锁.
     */
    private static void synchronizedCheckMethod() {
        Phone person = new Phone();

        new Thread(() -> {
            try {
                person.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                person.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }

}
