package com.interview.JDK8.Function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.zip.CheckedOutputStream;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/19.
 * <p>
 * JDK8 函数式编程的Demo:(java.util.function)
 * 4种函数式接口:
 * 1).消费型Consumer<T>;
 * 2).供给型Supplier<T>;
 * 3).函数型Function<T,R>;
 * 4).断定型Predicate<T>;
 */
public class FunctionalInterfaceDemo {

    public static void main(String[] args) {

        Consumer<String> consumer = s -> System.out.println(s);
        Supplier<String> supplier = () -> {
            return "supplier";
        };
        Function<String, Integer> function = s -> {
            return 1024;
        };
        Predicate<String> predicate = s -> {
            return s.isEmpty();
        };

        //验证:
        consumer.accept("consumer");
        System.out.println(supplier.get());
        System.out.println(function.apply("input"));
        System.out.println(predicate.test("predicate"));
    }
}
