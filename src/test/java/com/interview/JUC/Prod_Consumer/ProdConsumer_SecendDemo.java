package com.interview.JUC.Prod_Consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/17.
 * <p>
 * 生产者-消费者之_2.0版本((lock-await-signal)).
 */

//资源类.
class AirCondition_Second {
    //共享变量:
    private int number = 0;
    //使用ReentrantLock替代synchronized.
    private Lock lock = new ReentrantLock();
    //使用Condition中的方法替代.
    private Condition condition = lock.newCondition();


    //增加方法.
    public void increaseMethod() {
        lock.lock();
        try {
            //判断(为了防止虚假唤醒,多线程的判断需要使用loop循环中判断.)
            while (number != 0) {
                condition.await();
            }
            //操作
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //唤醒通知.
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //减少方法.
    public void decreaseMethod() {
        lock.lock();
        try {
            //判断
            while (number == 0) {
                try {
                    //操作
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //操作
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //唤醒通知.
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


}


public class ProdConsumer_SecendDemo {
}
