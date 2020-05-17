package com.interview.JUC.Prod_Consumer;

import java.util.Calendar;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/17.
 * <p>
 * Condition: 精确等待和唤醒.
 * 题目:
 * 多线程之间按顺序调用,实现A->B->C;
 * 启动要求如下:
 * AA打印5次,BB打印10次,CC打印15次
 * 接着
 * AA打印5次,BB打印10次,CC打印15次
 * ...
 * 共10轮.
 */


//资源类:
class ShareData {
    //标识位(默认先执行AA)
    private int number = 1; //A->1 B->2 C->3
    //锁
    private Lock lock = new ReentrantLock();
    //Condition对象(一把锁,需要多个钥私)
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    /**
     * 打印方法(type)
     * A->1 B->2 C->3
     */
    public void print5() {
        lock.lock();
        try {
            //判断:
            while (number != 1) {
                //wait.
                condition1.await();
            }
            //操作:AA->BB
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 2;
            //condition1完成后 -> 通知condition2.
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //判断:
            while (number != 2) {
                //wait.
                condition2.await();
            }
            //操作:AA->BB
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //判断:
            while (number != 3) {
                //wait.
                condition3.await();
            }
            //操作:AA->BB
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}


public class ConditionDemo {


    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.print5();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.print10();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.print15();
            }
        }, "CC").start();
    }
}
