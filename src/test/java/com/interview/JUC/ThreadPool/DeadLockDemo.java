package com.interview.JUC.ThreadPool;

import java.util.concurrent.TimeUnit;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/19.
 * <p>
 * 死锁Demo.
 * <p>
 * 定义:
 * 死锁是指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉那他们都将无法推进下去，如果系统资源充足，
 * 进程的资源请求都能够得到满足，死锁出现的可能性就很低，否则就会因争夺有限的资源而陷入死锁。
 * <p>
 * Demo说明:
 * threadA(线程A)
 * threadB(线程B)
 * lockA((锁A))
 * lockB((锁B))
 * threadA--持有-->lockA
 * threadB--试图获取-->lockA
 * threadB--持有-->lockB
 * threadA--试图获取-->lockB
 * <p>
 * 产生死锁的主要原因:
 * a.系统资源不足
 * b.进程运行推进的顺序不合适
 * c.资源分配不当
 * <p>
 * 排除定位:(*)
 * 1)-> 使用jps -l定位进程号
 * 2)-> jstack 进程号找到死锁查看
 */

//资源类:
class MyHandleLock implements Runnable {
    private String lockA;
    private String lockB;

    //构造方法:
    public MyHandleLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        //持有A锁
        synchronized (lockA) {
            System.out.println("ONE: " + Thread.currentThread().getName() + "\t持有锁:" + lockA + "\t等待锁:" + lockB);
            try {
                //暂停2秒.
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println("TWO: " + Thread.currentThread().getName() + "\t持有锁:" + lockB + "\t等待锁:" + lockA);
            }
        }


    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "A锁";
        String lockB = "B锁";

        //两个线程抢占资源,模拟死锁.
        new Thread(new MyHandleLock(lockA, lockB),"T1").start();
        new Thread(new MyHandleLock(lockB, lockA),"T2").start();
    }


}
