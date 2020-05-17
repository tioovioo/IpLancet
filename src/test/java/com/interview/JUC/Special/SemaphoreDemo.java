package com.interview.JUC.Special;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/16.
 * SemaphoreDemo: 信号灯
 * 经典例子:
 * 抢车位;
 * 抢红包.
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //抢车位示例:
        //初始车位数: 3
        System.out.println("========抢车位========");
        Semaphore semaphore = new Semaphore(3);

        //模拟6辆车 抢占3个车位:
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //占车位:
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到了车位.");
                    //使用2秒
                    System.err.println(Thread.currentThread().getName() + "\t使用中..");
                    TimeUnit.SECONDS.sleep(2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //归还车位(释放资源):
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "\t归还了车位.");
                }
            }, String.valueOf(i)).start();
        }


    }
}
