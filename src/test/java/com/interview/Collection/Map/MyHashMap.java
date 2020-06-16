package com.interview.Collection.Map;

import org.apache.tools.ant.taskdefs.Get;

/**
 * @author: yongjia.guo
 * Create Date : 2020/6/15.
 * <p>
 * 自定义HashMap（数组+链表）实现put和get方法。
 */
public class MyHashMap<K, V> implements Map<K, V> {

    //定义数组
    private Entry<K, V>[] table = null;
    private int size = 0;
    private static final int DEFAULT_LENGTH = 16;

    public MyHashMap() {
        //默认容量： 16
        table = new Entry[DEFAULT_LENGTH];
    }

    //重写put 和 get 方法。

    /**
     * put方法的步骤：
     * 1):key进行hash；
     * 2):得到对应数组的下标
     * 3):如果为null-> 插入
     * 4):不为null-> 放入链表。
     *
     * @param k
     * @param v
     * @return
     */

    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry<K, V> currentEntry = table[index];
        //没有数据
        if (currentEntry == null) {
            table[index] = new Entry<>(k, v, null, index);
        } else {
            table[index] = new Entry<>(k, v, currentEntry, index);
        }
        size++;
        return v;
    }

    /**
     * get方法对应步骤：
     * 1):key进行hash；
     * 2):得到对应数组的下标；
     * 3):比较key和hashCode值（递归或者循环）；
     * 4):返回结果；
     *
     * @param k
     * @return
     */
    @Override
    public V get(K k) {
        //当前map中没有元素。
        if (size == 0) {
            return null;
        }
        int index = hash(k);

        Entry<K, V> entry = getEntry(k, index);
        return entry == null ? null : entry.getValue();
    }

    //比较key和hashCode值（递归或者循环）
    private Entry<K, V> getEntry(K k, int index) {
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.hash == index && (k == entry.getKey() || k.equals(entry.getKey()))) {
                return entry;
            }
        }
        return null;
    }

    private int hash(K k) {
        int i = k.hashCode() % (DEFAULT_LENGTH - 1);
        return Math.abs(i);
    }


    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements Map.Entry<K, V> {

        K key;
        V value;
        Entry<K, V> next;
        int hash;

        public Entry(K key, V value, Entry<K, V> next, int hash) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}
