package com.tioovioo.JVM;

import org.junit.Test;

/**
 * @author: yongjia.guo
 * Create Date : 2020/4/7.
 * <p>
 * <p>
 * 演示jvm的作用过程。
 */
public class JvmTest {

    int i = 0;

    public static void main(String[] args) {
        int result = 300;
        System.out.println(result);

    }


    @Test
    public void t01() {
    }


    @Test
    public void t02() {

        Runtime rt = Runtime.getRuntime();
        System.gc();
        rt.gc();


    }


    /**
     * 测试栈内存溢出：
     * 递归。
     */
    @Test
    public void t03() {

        while (true) {
            i++;
            System.out.println(i); //10461
            t03();
        }

    }

    /**
     * 测试堆内存溢出：
     * new 对象
     */
    @Test
    public void t04() {

        //先分配堆内存的值 -Xms -Xmx
        while (true){
            i++;
            JvmTest j = new JvmTest();
            System.out.println(i);
        }

    }


}
