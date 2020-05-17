package com.interview.JUC.Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/16.
 * <p>
 * 概念:
 * 独占锁：指该锁一次只能被一个线程所持有，对ReentrantLock和Synchronized而言都是独占锁.
 * <p>
 * 共享锁：只该锁可被多个线程所持有
 * <p>
 * ReentrantReadWriteLock其读锁是共享锁，写锁是独占锁
 * <p>
 * 互斥锁：读锁的共享锁可以保证并发读是非常高效的，读写、写读、写写的过程是互斥的
 * <p>
 * 读写锁演示:
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程象取写共享资源来，就不应该自由其他线程可以对资源进行读或写
 * 总结
 * 读读能共存
 * 读写不能共存
 * 写写不能共存
 * <p>
 * 写操作-> 独占+原子操作(单个线程操作+完整性).
 */

//模拟Redis的操作: 支持共享读,和独占写.
class MyCache {

    //共享资源:(volatil->保证数据修改后的全部线程可见.)
    private volatile Map<String, Object> map = new HashMap<>();

    //读写锁(默认时非公平锁)
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //写操作:
    public void write(String key, Object value) {

        //加写锁.
        readWriteLock.writeLock().lock();
        try {
            System.err.println(Thread.currentThread().getName() + "\t写入数据【Start】");
            //暂停300毫秒,模拟写操作.
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);

            System.err.println(Thread.currentThread().getName() + "\t写入数据【End】");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放写锁
            readWriteLock.writeLock().unlock();
        }
    }

    //读操作:
    public void read(String key) {

        try {
            readWriteLock.readLock().lock();
            System.err.println(Thread.currentThread().getName() + "\t读取数据【Start】");
            //暂停300毫秒,模拟写操作.
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Object result = map.get(key);
            System.err.println(Thread.currentThread().getName() + "\t读取数据【End】");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    //清空.
    public void clear() {
        map.clear();
    }

}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        /**
         模拟Redis实现同时读取,独占写入操作.
         5个线程同时读取,同时写入.
         */

        System.out.println("=======模拟Redis实现同时读取,独占写入操作=========");
        for (int i = 1; i <= 5; i++) {
            //传值到lambda中,需要使用final修饰才能被引用.
            final int key = i;
            new Thread(() -> {
                myCache.write(String.valueOf(key), String.valueOf(key));
            }, String.valueOf(i)).start();

        }

        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 5; i++) {
            final int key = i;
            new Thread(() -> {
                myCache.read(String.valueOf(key));
            }, String.valueOf(i)).start();
        }

    }
}
