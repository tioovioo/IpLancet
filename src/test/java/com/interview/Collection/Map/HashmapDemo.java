package com.interview.Collection.Map;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author: yongjia.guo
 * Create Date : 2020/6/15.
 * <p>
 * HashMap的知识点Demo。
 * 底层数据结构： 数组+链表+红黑树。
 * 数组： 查询-> O(1) 增删-> O(n); （查找只需要a[0]指定索引即可）
 * 链表： 查询-> O(n) 增删-> O(1);（查找需要遍历链表， 增删只需要修改节点的引用即可。）
 * <p>
 * Entry<K,V>对象包括：
 * key；value；hash；next
 * 默认值：
 * 默认容量：16；2的4次方
 * 链表转红黑树的阈值：8
 * 负载因子： 0.75f
 */
public class HashmapDemo {

    public static void main(String[] args) {
        //自定义hashMap测试：
        MyHashMap<String, String> myMap = new MyHashMap<>();

        for (int i = 0; i < 100; i++) {
            String put = myMap.put("h" + i, "Hello World!\t" + i);
            System.out.println(myMap.get("h" + i));

        }
        System.out.println(myMap.size());
    }

}
