package com.interview.JUC.Volatile;

import com.lancet.iplancet.util.StringUtil;
import org.apache.poi.ss.formula.functions.MinaMaxa;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/13.
 * <p>
 * Volatile 详解:
 * 1).可见性;
 * 2).不保证原子性;
 * 3). 禁止指令重排(有序性)
 */

class MyData {
    //主内存变量：
//     int number = 0;
    volatile int number = 0;


    //验证可见性.
    public void changeTo60() {
        this.number = 60;
    }

    //i++ 验证原子性. 加synchronized可保证.
    public void plusPlus() {
        number++;
    }

    /**
     * 解决volatile不保证原子性的方法:
     * 1): 使用synchronized 同步(不建议,大材小用);
     * 2): 使用atomicInteger的getAndIncrement()方法保证原子操作.
     */
    AtomicInteger myAtomicInteger = new AtomicInteger();

    public void myAtomicInteger() {
        myAtomicInteger.getAndIncrement();
    }


}


public class VolatileDemo {

    public static void main(String[] args) {

        System.out.println(Thread.activeCount());
    }

    /**
     * 可见性:
     * 模拟main线程和自定义线程AAA 操作主内存时，使用volatile保证。
     */
    @Test
    public void t01() {
        VolatileVisibility();
    }


    /**
     * 不保证原子性:
     * i++ 操作示范。
     */
    @Test
    public void t02() {
        VolatileAtomic();
    }

    private void VolatileAtomic() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                //循环1000次.
                for (int j = 0; j < 1000; j++) {
                    //不保证原子操作:
                    myData.plusPlus();
                    //保证原子操作:
                    myData.myAtomicInteger();
                }
            }, String.valueOf(i)).start();
        }

        //等待上述线程执行完成:(默认有两个线程正在工作 : main 和 gc线程)
        while (Thread.activeCount() > 2) {
            //使当前线程从执行状态（运行状态）变为可执行态（就绪状态）
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t type: int current number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t type: atomicInteger current number value:" + myData.myAtomicInteger);

    }


    private void VolatileVisibility() {
        MyData myData = new MyData();

        //线程：
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");

            try {

                //暂停3秒钟.
//                Thread.sleep(3000);
                TimeUnit.SECONDS.sleep(3);
                //修改值:
                myData.changeTo60();
                System.out.println(Thread.currentThread().getName() + "\t update value:" + myData.number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AAA").start();


        while (myData.number == 0) {
            /**
             * 验证可见性:
             *  如果不可见-> 一直停在此处;
             *  如果保证可见性-> 跳出循环.
             */
        }

        System.out.println(Thread.currentThread().getName() + "\t current number value:" + myData.number);
    }


}
