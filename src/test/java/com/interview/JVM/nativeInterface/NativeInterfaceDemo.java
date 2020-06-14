package com.interview.JVM.nativeInterface;

/**
 * @author: yongjia.guo
 * Create Date : 2020/6/2.
 *
 * JVM_本地接口.
 */
public class NativeInterfaceDemo {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();  //java.lang.IllegalThreadStateException


    }

}
