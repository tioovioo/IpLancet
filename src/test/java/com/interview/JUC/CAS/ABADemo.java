package com.interview.JUC.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/14.
 * <p>
 * ABA问题:
 * CAS算法实现一个重要前提需要去除内存中某个时刻的数据并在当下时刻比较并替换，那么在这个时间差类会导致数据的变化。
 * <p>
 * 比如线程1从内存位置V取出A，线程2同时也从内存取出A，并且线程2进行一些操作将值改为B，然后线程2又将V位置数据改成A，这时候线程1进行CAS操作发现内存中的值依然时A，然后线程1操作成功。
 * <p>
 * ==尽管线程1的CAS操作成功，但是不代表这个过程没有问题==
 * <p>
 * 解决:
 * 1).原子引用.
 * 2).新增机制，修改版本号(时间戳)
 */
public class ABADemo {

    //主内存中的初始变量值为->"A"
    static AtomicReference<String> atomicReference = new AtomicReference<>("A");
    //解决ABA问题:(增加版本号: stamp->1)
    static AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("A", 1);

    public static void main(String[] args) {

        //模拟ABA问题.
        System.err.println("=====模拟ABA问题======");
        new Thread(() -> {
            //T1线程模拟ABA 操作.
            atomicReference.compareAndSet("A", "B");
            atomicReference.compareAndSet("B", "A");
        }, "T1").start();

        new Thread(() -> {
            try {
                //线程T1和T2:未保证T1先执行,所以T2暂停一秒,等待T1先执行.
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet("A", "Final Value") + "\t-> 结果: " + atomicReference.get());

        }, "T2").start();

        //等待上述线程执行完成.
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        //解决ABA问题.
        System.err.println("=====解决ABA问题======");
        new Thread(() -> {
            //获取第一次的版本号:
            int firstStamp = stampedReference.getStamp();

            try {
                //暂停1秒,等待T4能够拿到第一次版本号.
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //模拟ABA问题:
            stampedReference.compareAndSet("A", "B", stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println("当前线程:\t" + Thread.currentThread().getName() + "\t操作:A->B对应版本号:\t" + stampedReference.getStamp());

            stampedReference.compareAndSet("B", "A", stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println("当前线程:\t" + Thread.currentThread().getName() + "\t操作:B->A对应版本号:\t" + stampedReference.getStamp());

        }, "T3").start();

        new Thread(() -> {
            //获取第一次的版本号:
            int firstStamp = stampedReference.getStamp();

            try {
                //暂停3秒,等待T3执行完成ABA操作.
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //解决: ABA问题的结果
            boolean result = stampedReference.compareAndSet("A", "Final Value", firstStamp, firstStamp + 1);

            System.out.println("当前线程:\t" + Thread.currentThread().getName() + "\t执行结果" + result);
            System.out.println("主内存中的当前值:\t" + stampedReference.getReference() + "\t主内存中的当前版本号:" + stampedReference.getStamp());
        }, "T4").start();

    }
}
