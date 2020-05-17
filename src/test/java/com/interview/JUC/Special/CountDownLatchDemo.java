package com.interview.JUC.Special;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/16.
 * <p>
 * 演示CountDownLatch的使用(倒计时).
 * 特点:
 * 1).它允许一个或多个线程一直等待，知道其他线程的操作执行完后再执行。
 * 例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行
 * <p>
 * 2).CountDownLatch主要有两个方法，当一个或多个线程调用await()方法时，
 * 调用线程会被阻塞。其他线程调用countDown()方法会将计数器减1，
 * 当计数器的值变为0时，因调用await()方法被阻塞的线程才会被唤醒，继续执行
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {

        /**
         * 模拟倒计时过程:
         * 要求:
         *  班长在所有人之后离开.
         */
//        LockDoor();

        //演示枚举:(战国七雄)
        SengokuUnite();
    }

    private static void SengokuUnite() throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.err.println(Thread.currentThread().getName() + "\t被灭...");
                downLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getCountryName()).start();
        }

        //方法二:(Count变为0之后,才执行之后的操作)
        downLatch.await();
        System.out.println("秦国统一天下...");
    }

    //演示倒计时操作.
    private static void LockDoor() throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.err.println(Thread.currentThread().getName() + "\t离开教室...");
                downLatch.countDown();
            }, String.valueOf(i)).start();
        }

        //方法一:
//        while (Thread.activeCount()>2){
//            Thread.yield();
//        }

        //方法二:(Count变为0之后,才执行之后的操作)
        downLatch.await();
        System.out.println("班长锁门...");
    }
}
