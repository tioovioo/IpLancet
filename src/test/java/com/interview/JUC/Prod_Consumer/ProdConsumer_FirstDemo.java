package com.interview.JUC.Prod_Consumer;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/17.
 * <p>
 * * 生产者-消费者之_1.0版本. (synchronized-wait-notify)
 * *
 * * 上:
 * * 	高内聚,低耦合,线程操作资源类;
 * * 下:
 * * 	判断,操作,通知.
 * * 横:
 * * 	防止多线程并发状态下的虚假唤醒.
 * *
 * * 	题目: 一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮(保证线程安全).
 */

//资源类.
class AirCondition {
    //共享变量:
    private int number = 0;

    //增加方法.
    public synchronized void increaseMethod() {
        //判断(为了防止虚假唤醒,多线程的判断需要使用loop循环中判断.)
        while (number != 0) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //操作
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //唤醒通知.
        this.notifyAll();
    }

    //减少方法.
    public synchronized void decreaseMethod() {
        //判断
        while (number == 0) {
            try {
                //操作
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //操作
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //唤醒通知.
        this.notifyAll();

    }


}

public class ProdConsumer_FirstDemo {

    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();

        //模拟交替操作: A->增加 B->减少.
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                airCondition.increaseMethod();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                airCondition.decreaseMethod();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                airCondition.increaseMethod();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                airCondition.decreaseMethod();
            }
        }, "BB").start();
    }
}
