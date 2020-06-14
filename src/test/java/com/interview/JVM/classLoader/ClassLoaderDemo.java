package com.interview.JVM.classLoader;

/**
 * @author: yongjia.guo
 * Create Date : 2020/6/2.
 * <p>
 * //验证ClassLoader的过程.
 * <p>
 * 双亲委派机制:
 *定义:
 * 指在加载一个类的时候，JVM会判断这个类是否已经被其类加载器加载过了。
 * 如果已经加载过了，那么直接返回这个类。
 * 如果没有加载，就使用这个类对应的加载器的父类加载器判断，
 * 一层一层的往上判断，最终会由BootstrapClassLoader判断。
 * 如果BootstrapClassLoader判断都没有加载这个类,
 * 那么就由BootstrapClassLoader尝试加载。 如果BootstrapClassLoader加载失败了，
 * 就由BootstrapClassLoader的子类加载器们加载。
 *
 * 目的:
 * 是为了防止类被重复加载，避免核心API遭到恶意破坏,保证了java源代码的安全性
 * 如Object类，它由BootstrapClassLoader在JVM启动时加载。
 * 如果没有双亲委派机制，那么Object类就可以被重写，其带来的后果将无法想象。
 *
 * 沙箱安全机制:
 * 是由基于双亲委派机制上，采取的一种JVM的自我保护机制,假设自己写一个java.lang.String 的类,
 * 在类中自定义方法，由于双亲委派机制的原理,此请求会先交给Bootstrap试图进行加载,
 * 但是Bootstrap在加载类时首先通过包和类名查找rt.jar中有没有该类,
 * 有则优先加载rt.jar包中的类,没有自定义方法会报错，因此就保证了java的运行机制不会被破坏。
 *
 * 问题:
 * 1)什么是类加载器(加载class文件->Class);
 * 2)有几种;
 * 3)双亲委派;
 * 4)沙箱安全机制.
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        // jre/lib/rt.jar中有Object.class  启动类加载器直接加载
        Object o = new Object();
        ClassLoader classLoader = o.getClass().getClassLoader();
        System.out.println(classLoader); //null

        // javax  随着发展而后扩展的 /jre/lib/ext/下
    }

}
