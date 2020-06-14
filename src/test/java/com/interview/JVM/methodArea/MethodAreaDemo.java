package com.interview.JVM.methodArea;

/**
 * @author: yongjia.guo
 * Create Date : 2020/6/4.
 * <p>
 * 方法区(线程共享.):
 * 存储每一个类的结构信息.
 */
public class MethodAreaDemo {
    

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
