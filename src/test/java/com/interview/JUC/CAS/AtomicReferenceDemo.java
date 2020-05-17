package com.interview.JUC.CAS;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/13.
 * 原子引用包装类.
 *
 */

@Data
@AllArgsConstructor
class Student {

    String name;
    int age;
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        Student s1 = new Student("S1", 23);
        Student s2 = new Student("S2", 25);

        AtomicReference<Student> atomicReference = new AtomicReference<>();
        atomicReference.set(s1);
        System.out.println("原始值:\t" + atomicReference.get());
        System.out.println("替换后的值:\t" + atomicReference.compareAndSet(s1, s2) + "\t" + atomicReference.get());
        System.out.println("替换后的值:\t" + atomicReference.compareAndSet(s1, s2) + "\t" + atomicReference.get());
    }
}
