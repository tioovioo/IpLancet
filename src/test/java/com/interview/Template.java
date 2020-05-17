package com.interview;

import java.util.concurrent.TimeUnit;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/16.
 *
 * 模板.
 */
public class Template {

    public static void main(String[] args) {
        //try-catch-finally
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        //for-thread
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
            }, String.valueOf(i)).start();
        }

        //thread-sleep
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
