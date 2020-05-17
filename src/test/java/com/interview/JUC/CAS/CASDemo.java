package com.interview.JUC.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/13.
 *
 * CAS 比较并交换(compareAndSwap)
 *  1).失败->真实值和期望值不相同,不会发生修改;
 *  1).成功->真实值和期望值相同,修改.
 *
 *  Unsafe类保证原子性(CPU源语 不允许中断.):
 *      getAndAddInt();
 *      compareAndSwapInt();
 *
 *
 * CAS的应用:
 *  CAS有3个操作数,内存值V, 旧的预期值A,要修改更新值B.
 *  当且仅当预期值A与内存值V相同是,将内存值V修改为B,否则什么也不做.
 *
 * CAS的缺点:
 *  1). ** 循环时间长，开销大**
 *      例如getAndAddInt方法执行，有个do while循环，如果CAS失败，一直会进行尝试，
 *      如果CAS长时间不成功，可能会给CPU带来很大的开销
 *  2). 只能保证一个共享变量的原子操作
 *      对多个共享变量操作时，循环CAS就无法保证操作的原子性，这个时候就可以用锁来保证原子性.
 *  3). ABA问题.
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger myAtomic = new AtomicInteger(5);

        System.out.println("操作结果:\t"+myAtomic.compareAndSet(5,100)+ "\t当前值: "+myAtomic);
        System.out.println("操作结果:\t"+myAtomic.compareAndSet(5,120)+ "\t当前值: "+myAtomic);

        myAtomic.getAndIncrement();


        /**
         *
         * 源码分析:
         * //unsafe.getAndAddInt
         *     public final int getAndAddInt(Object var1, long var2, int var4) {
         *         int var5;
         *         do {
         *             var5 = this.getIntVolatile(var1, var2);
         *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
         *         return var5;
         *     }
         * var1 AtomicInteger对象本身
         *
         * var2 该对象的引用地址
         *
         * var4 需要变动的数据
         *
         * var5 通过var1 var2找出的主内存中真实的值
         *
         * 用该对象前的值与var5比较；
         *
         * 如果相同，更新var5+var4并且返回true，
         *
         * 如果不同，继续去之然后再比较，直到更新完成
         *
         *
         */


    }
}
