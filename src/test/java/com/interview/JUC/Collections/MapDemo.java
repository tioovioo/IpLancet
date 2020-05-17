package com.interview.JUC.Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/15.
 *
 * 集合类的线程不安全之 HashMap:
 *
 *
 */
public class MapDemo {

    public static void main(String[] args) {
        HashMapConcurrentEDemo();
    }

    private static void HashMapConcurrentEDemo() {
        //演示线程不安全: java.util.ConcurrentModificationException.
//        Map<String,String> map = new HashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();

//        Set<String> list = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
