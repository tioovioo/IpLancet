package com.interview.JUC.Collections;

import org.junit.Test;

import java.io.StringReader;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/14.
 * <p>
 * 集合类的线程不安全之 ArrayList.
 * 1)现象:
 *      java.util.ConcurrentModificationException
 * 2)原因:
 *      get()和add()方法没用synchronized修饰也没有加同步.
 *
 * 3)解决方案:
 *      a-> new Vector<>(); 不推荐(效率低);
 *      b-> Collections.synchronizedList(new ArrayList<>());
 *      c-> new CopyOnWriteArrayList<>();
 * 4)优化建议:
 *
 *
 */
public class ListDemo {
    public static void main(String[] args) {
        //例子:
        List<String> myList = Arrays.asList("A", "B", "C");
        myList.forEach(System.out::println);

        // 模拟循环添加时的异常.
        ArrayListConcurrentEDemo();

    }


    /**
     * CopyOnWrite:
     * 写时复制,读写分离:
     * CopyOnWrite容器即写时复制，往一个元素添加容器的时候，
     * 不直接往当前容器Object[]添加，而是先将当前容器Object[]进行copy，
     * 复制出一个新的容器Object[] newElements，让后新的容器添加元素，
     * 添加完元素之后，再将原容器的引用指向新的容器setArray(newElements),
     * 这样做可以对CopyOnWrite容器进行并发的读，而不需要加锁，
     * 因为当前容器不会添加任何元素，所以CopyOnWrite容器也是一种读写分离的思想，
     * 读和写不同的容器(读->旧的容器 写->新的容器)
     *
     * 源码:
     *         final ReentrantLock lock = this.lock;
     *         lock.lock();
     *         try {
     *             Object[] elements = getArray();
     *             int len = elements.length;
     *             Object[] newElements = Arrays.copyOf(elements, len + 1);
     *             newElements[len] = e;
     *             setArray(newElements);
     *             return true;
     *         } finally {
     *             lock.unlock();
     *         }
     */

    /**
     * 模拟异常:
     * java.util.ConcurrentModificationException
     */
    private static void ArrayListConcurrentEDemo() {
//        List<String> list = new ArrayList<>();
        //方法一: Vector->替换 ArrayList
//        List<String> list = new Vector<>();
        //方法二:
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        //方法三:
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }


    /**
     * 模拟遍历时删除的异常.
     */
    @Test
    public void t01() {
        //例子:
        List<String> list = Arrays.asList("A", "B", "C");
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if ("A".equals(s)) {
                list.remove(i);
            }
        }

    }


}
