package com.interview.JUC.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/15.
 * <p>
 * 自己实现自旋锁.
 * 自旋锁好处，循环比较获取知道成功位置，没有类似wait的阻塞
 * <p>
 * 通过CAS操作完成自旋锁，A线程先进来调用mylock方法自己持有锁5秒钟，
 * B随后进来发现当前有线程持有锁，不是null，所以只能通过自旋等待，知道A释放锁后B随后抢到
 */
public class SpinLockDemo {


    //共享变量(原子引用线程):
    AtomicReference<Thread> reference = new AtomicReference<>();

    //模拟CAS(while) 实现自旋锁(加锁)
    public void spinLock() {

        //如果第一次执行,因为线程为null,所以不进入循环体
        while (!reference.compareAndSet(null, Thread.currentThread())) {

        }
        System.err.println(Thread.currentThread().getName() + "\t上锁成功!");

    }

    //(解锁)
    public void spinUnLock() {

        Thread currentThread = Thread.currentThread();

        //如果比较相同,将锁解开.
        reference.compareAndSet(currentThread, null);

        System.err.println(Thread.currentThread().getName() + "\t解锁成功!");
    }


    public static void main(String[] args) {

        SpinLockDemo lockDemo = new SpinLockDemo();
        //模拟自旋锁操作:
        System.out.println("======模拟自旋锁操作=====");
        new Thread(() -> {
            //上锁
            lockDemo.spinLock();
            //T1-> 暂停5秒(这期间,其他获取线程就处于自旋等待状态.)
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //解锁
            lockDemo.spinUnLock();
        }, "T1").start();


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //上锁
                lockDemo.spinLock();
                //T1-> 暂停1秒
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //解锁
                lockDemo.spinUnLock();
            }, String.valueOf(i)).start();
        }


    }

}
