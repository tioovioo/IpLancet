package com.interview.JDK8.Stream;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/19.
 * <p>
 * StreamDemo.
 */

//开启链式编程.
@Accessors(chain = true)
@Data
@NoArgsConstructor
class User {

    private int id;
    private String userName;
    private int age;

    public User(int id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }
}

/**
 * 题目:
 * 请按照给出的数据,找出同时满足以下条件的用户:
 * 1):ID为偶数;
 * 2):年龄大于24;
 * 3):用户名转为大写;
 * 4):用户名字母倒序排列;
 * 5):只输出一个用户的名字.
 */
public class StreamDemo {

    public static void main(String[] args) {

        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 25);
        User u4 = new User(14, "d", 26);
        User u5 = new User(15, "e", 27);
        User u6 = new User(16, "f", 28);


        List<User> list = Arrays.asList(u1, u2, u3, u4, u5, u6);

//        list = list.stream()
//                .filter(user -> user.getId() % 2 == 0 && user.getAge() > 24)
//                .map(m -> {
//                    m.getUserName().toUpperCase();
//                }).sorted()
//                .collect(Collectors.toList());
        list.forEach(System.out::println);


    }
}
