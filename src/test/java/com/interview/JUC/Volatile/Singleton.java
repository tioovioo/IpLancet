package com.interview.JUC.Volatile;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/14.
 * <p>
 * 优化版本的单例模式:
 * 1): DCL;
 * 2): 禁止指令重排.
 */
public class Singleton {

    //禁止指令重排.
    private static volatile Singleton instance = null;

    //私有化构造方法;
    private Singleton() {
        System.err.println(Thread.currentThread().getName() + "\t+ create  instance!");
    }


    /**
     * 懒汉式
     * DCL(double check lock)双重检查机制.
     * 缺点: 线程不安全,因为有指令重排序.
     * <p>
     * instance = new Singleton()包含3步操作:
     * memory = allocate();//1.分配对象内存空间
     * instance(memory);	//2.初始化对象
     * instance = memory;	//3.设置instance执行刚分配的内存地址，此时instance!=null
     * 步骤2和步骤3不存在数据依赖关系，而且无论重排前还是重排后程序的执行结果在单线程中并没有改变，
     * 因此这种重排优化时允许的，如果3步骤提前于步骤2，但是instance还没有初始化完成
     * <p>
     * 但是指令重排只会保证串行语义的执行的一致性（单线程），但并不关心多线程间的语义一致性。
     * <p>
     * ==所以当一条线程访问instance不为null时，由于instance示例未必已初始化完成，也就造成了线程安全问题。==
     */

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }

        }
        return instance;
    }


    public static void main(String[] args) {

        //单线程版:
//        System.out.println(Singleton.getInstance() == Singleton.getInstance()); //true
//        System.out.println(Singleton.getInstance() == Singleton.getInstance()); //true
//        System.out.println(Singleton.getInstance() == Singleton.getInstance()); //true

        //多线程版本:
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                /**
                 * 未优化版本: 实例个数大于一次;
                 * 优化方法:
                 *  1). synchronized
                 *  2).
                 *
                 */
                Singleton.getInstance();
            }, String.valueOf(i)).start();
        }
    }

}
