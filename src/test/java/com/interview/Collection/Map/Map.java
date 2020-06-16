package com.interview.Collection.Map;

/**
 * @author: yongjia.guo
 * Create Date : 2020/6/15.
 * 自定义Map接口。
 */
public interface Map<K, V> {
    public V get(K k);

    public V put(K k,V v);

    public int size();

    interface Entry<K, V> {
        public K getKey();

        public V getValue();
    }
}
