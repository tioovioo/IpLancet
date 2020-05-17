package com.interview.JUC.Prod_Consumer;

import java.util.concurrent.TimeUnit;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/17.
 * <p>
 * 测试synchronized:
 * 1).->对象锁(当前对象);
 * 2).->类锁(Class).
 * <p>
 * 3种常见情况:
 * a. 普通方法->对象锁;
 * b. 同步方法块->锁的是括号中的对象;
 * c. 静态同步方法(static)->类锁.
 * <p>
 * 模拟synchronized锁的各种情况:
 *
 *
 * synchronized和lock有什么区别？用新的lock有什么好处？请举例
 *
 * 区别
 * 原始构成
 *
 * synchronized时关键字属于jvm
 *
 * monitorenter，底层是通过monitor对象来完成，其实wait/notify等方法也依赖于monitor对象只有在同步或方法中才能掉wait/notify等方法
 *
 * monitorexit
 *
 * Lock是具体类，是api层面的锁（java.util.）
 *
 * 使用方法
 *
 * sychronized不需要用户取手动释放锁，当synchronized代码执行完后系统会自动让线程释放对锁的占用
 * ReentrantLock则需要用户去手动释放锁若没有主动释放锁，就有可能导致出现死锁现象，需要lock()和unlock()方法配合try/finally语句块来完成
 * 等待是否可中断
 *
 * synchronized不可中断，除非抛出异常或者正常运行完成
 * ReentrantLock可中断，设置超时方法tryLock(long timeout, TimeUnit unit)，或者lockInterruptibly()放代码块中，调用interrupt()方法可中断。
 * 加锁是否公平
 *
 * synchronized非公平锁
 * ReentrantLock两者都可以，默认公平锁，构造方法可以传入boolean值，true为公平锁，false为非公平锁
 * 锁绑定多个条件Condition
 *
 * synchronized没有
 * ReentrantLock用来实现分组唤醒需要要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。
 */

//共享资源类.
class Phone {

    //发送邮件.
    public  static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.err.println("sendEmail...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送短信.
    public static synchronized void sendMessage() {
        System.err.println("sendMessage...");
    }

    //普通方法.
    public void normalMethod() {
        System.err.println("NormalMethod...");
    }
}


public class SynchronizedDemo {

    public static void main(String[] args) throws Exception{
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "T1").start();

        Thread.sleep(100);
        new Thread(() -> {
            phone2.sendMessage();
        }, "T2").start();

    }
}
