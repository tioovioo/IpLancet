package com.interview.JUC.Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/16.
 * <p>
 * BlockingQueue(阻塞队列) 相当于 List 接口
 * <p>
 * 代表:
 * **==ArrayBlockingQueue==**是一个基于数组结构的有界阻塞队列，
 * 此队列按FIFO原则对元素进行排序
 * **==LinkedBlockingQueue==**是一个基于链表结构的阻塞队列，
 * 此队列按FIFO排序元素，吞吐量通常要高于ArrayBlockingQueue
 * **==SynchronousQueue==**是一个不存储元素的阻塞队列，
 * 一个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态.
 * <p>
 * 作用:
 * 在多线程领域：所谓阻塞，在某些情况下会挂起线程，一旦满足条件，
 * 被挂起的线程又会自动被唤醒
 * <p>
 * 为什么需要BlockingQueue
 * <p>
 * 好处时我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，
 * 因为这一切BlockingQueue都给你一手包办了
 * <p>
 * 应用:
 * 1).异常;
 * 2).特殊值;
 * 3).阻塞;
 * 4).超时.
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws Exception {
        //演示阻塞队列之_ArrayBlockingQueue

        //需要一个初始值:
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        /**
         * 1).异常:
         *      a.插入: add(); ->java.lang.IllegalStateException: Queue full
         *      b.移除: remove(); java.util.NoSuchElementException
         *      c.检查: element(); 返回队列中的第一个元素.
         *
         */
//        ExceptionDemo(arrayBlockingQueue);

        /**
         * 2).特殊值:
         *      a.插入: offer(); ->false
         *      b.移除: poll(); -> null
         *      c.检查: peek(); 返回队列中的第一个元素.
         */
//        specialValueDemo(arrayBlockingQueue);

        /**
         * 3).阻塞:
         *      a.插入: put(); ->一直阻塞
         *      b.移除: take(); -> 一直阻塞
         *      c.检查: 无.
         */
        blockDemo(arrayBlockingQueue);
        //一直阻塞
//        arrayBlockingQueue.take();

        /**
         * 4).超时:
         *      a.插入: offer(long TimeOut); ->超时后退出阻塞队列 false
         *      b.移除: poll(long TimeOut); ->超时后退出阻塞队列 null
         *      c.检查: 无.
         */

    }

    private static void blockDemo(BlockingQueue<String> arrayBlockingQueue) throws InterruptedException {
        arrayBlockingQueue.put("A");
        arrayBlockingQueue.put("A");
        arrayBlockingQueue.put("A");
        //一直阻塞
//        arrayBlockingQueue.put("X");

        arrayBlockingQueue.take();
        arrayBlockingQueue.take();
        arrayBlockingQueue.take();
    }

    private static void specialValueDemo(BlockingQueue<String> arrayBlockingQueue) {
        arrayBlockingQueue.offer("A");
        arrayBlockingQueue.offer("A");
        arrayBlockingQueue.offer("A");
        System.out.println(arrayBlockingQueue.offer("B")); //false

        arrayBlockingQueue.poll();
        arrayBlockingQueue.poll();
        arrayBlockingQueue.poll();
        System.out.println(arrayBlockingQueue.poll()); //null

        System.out.println(arrayBlockingQueue.peek());
    }

    private static void ExceptionDemo(BlockingQueue<String> arrayBlockingQueue) {
        //当阻塞队列是满时，从队列中==添加==元素的操作会被阻塞.
        arrayBlockingQueue.add("A");
        arrayBlockingQueue.add("B");
        arrayBlockingQueue.add("C");
        //java.lang.IllegalStateException: Queue full
//        arrayBlockingQueue.add("D");


        //当阻塞队列是空是，从队列中==获取==元素的操作会被阻塞
        System.out.println(arrayBlockingQueue.element());
        System.out.println(arrayBlockingQueue.element());
        System.out.println(arrayBlockingQueue.element());
        arrayBlockingQueue.remove();
        arrayBlockingQueue.remove();
        arrayBlockingQueue.remove();
        //java.util.NoSuchElementException
//        arrayBlockingQueue.remove();
    }


}
