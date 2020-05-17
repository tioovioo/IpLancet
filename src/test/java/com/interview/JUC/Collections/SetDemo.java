package com.interview.JUC.Collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/15.
 *
 * 集合类的线程不安全之 HashSet:
 *  HashSet底层:就是HashMap
 *      map = new HashMap<>();
 * 解决方案:
 *      1)->: Collections.synchronizedSet(new HashSet<>());
 *      2)-> new CopyOnWriteArraySet<>().
 *
 * 面试题:
 *  底层?:
 *      hashMap;
 *  为什么add()只有一个参数,而map的put()有两个参数(K,V)?
 *      map.put(E,new Object): set只关心key,value是一个Object类型的常量.
 *
 */
public class SetDemo {

    public static void main(String[] args) {
        HashSetConcurrentEDemo();
    }

    private static void HashSetConcurrentEDemo() {
        //演示线程不安全: java.util.ConcurrentModificationException.
//        Set<String> list = new HashSet<>();

//        Set<String> list = Collections.synchronizedSet(new HashSet<>());
        Set<String> list = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }
}
