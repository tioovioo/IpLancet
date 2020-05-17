package com.interview.JUC.Special;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/16.
 * <p>
 * CyclicBarrier.
 * 演示: 七龙珠召唤神龙.
 */
public class CyclicBarrierDemo {
    final static int TotalNumber = 7;

    public static void main(String[] args) {
        System.out.println("=====七龙珠召唤神龙.=====");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(TotalNumber, () -> {
            System.err.println("召唤神龙.......");
        });

        //收集7颗龙珠.
        for (int i = 1; i <= 7; i++) {
            final int number = i;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集第: " + number + " 颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
