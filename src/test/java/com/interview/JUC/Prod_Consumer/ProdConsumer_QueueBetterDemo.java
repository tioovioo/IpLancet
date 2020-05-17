package com.interview.JUC.Prod_Consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/17.
 * <p>
 * 生产者-消费者之_3.0版本.(推荐)
 * 阻塞队列版.(保证生产一个就必须消费一个.)
 */


//资源类:
class MySource {
    //标识位:(使用volatile保证可见性)
    private volatile boolean FLAG = true; //默认开启(生产-消费)
    //原子操作 ++i和i++
    private AtomicInteger atomicInteger = new AtomicInteger();
    //阻塞队列:(推荐写为接口,使用构造方法赋值为具体的实现类)
    private BlockingQueue<String> queue = null;

    public MySource(BlockingQueue<String> queue) {
        //打印实现类的名称.
        System.out.println(queue.getClass().getName());
        this.queue = queue;
    }

    //生产:
    public void product() {
        try {
            String cake = null;
            boolean result;
            //判断:
            while (FLAG) {
                //开启->可以生产(++i操作.)
//                cake = String.valueOf(atomicInteger.incrementAndGet());
                cake = atomicInteger.incrementAndGet() + "";
                //将蛋糕放入队列中.2L-> how long to wait before giving up.
                result = queue.offer(cake, 2L, TimeUnit.SECONDS);
                if (result) {
                    System.out.println(Thread.currentThread().getName() + "\t生产 " + cake + " 成功,并放入队列.");
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t生产失败.");
                }
                //每隔1秒钟生产一个.
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("老板叫停操作,生产结束...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //消费:
    public void consumer() {
        try {
            String cake = null;
            //判断:
            while (FLAG) {
                //开启->可以消费(如果失败,2秒后退出.)
                cake = queue.poll(2L, TimeUnit.SECONDS);

                //获取失败
                if (null == cake || "".equals(cake)) {
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName() + "\t未获取到蛋糕,2秒后退出");
                    System.out.println();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "消费成功:\t" + cake);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //结束方法.:
    public void stop() throws Exception {
        this.FLAG = false;
    }

}


public class ProdConsumer_QueueBetterDemo {
    public static void main(String[] args) {

        MySource mySource = new MySource(new ArrayBlockingQueue<String>(10));

        //生产线程.
        new Thread(() -> {
            System.err.println("========生产线程启动============");
            mySource.product();
        }, "Product").start();

        //消费线程.
        new Thread(() -> {
            System.err.println("========消费线程启动============");
            mySource.consumer();
        }, "Consumer").start();


        //模拟停止操作:
        try {
            //5秒后,线程停止.
            TimeUnit.SECONDS.sleep(5);
            mySource.stop();
            System.err.println("====5秒后,停止操作=====");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
