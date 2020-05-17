package com.interview.JUC.Collections;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/15.
 * 值传递：(原值不变)
 * (形式参数类型是基本数据类型)：方法调用时，实际参数把它的值传递给对应的形式参数，
 * 形式参数只是用实际参数的值初始化自己的存储单元内容，是两个不同的存储单元，
 * 所以方法执行中形式参数值的改变不影响实际参数的值。
 * <p>
 * 引用传递：
 * (形式参数类型是引用数据类型参数)：也称为传地址。
 * 方法调用时，实际参数是对象(或数组)，这时实际参数与形式参数指向同一个地址，
 * 在方法执行中，对形式参数的操作实际上就是对实际参数的操作，
 * 这个结果在方法结束后被保留了下来，所以方法执行中形式参数的改变将会影响实际参数。
 */

@Data
@NoArgsConstructor
class Student {
    private String name;
    private int age;

    public Student(String name) {
        this.name = name;
    }

}

public class TransferDemo {


    @Test
    public void main() {
        TransferDemo demo = new TransferDemo();
        int age = 10;
        transferValue(age);
        System.err.println(age); //10


        Student student = new Student("张三");
        transferReference(student);
        System.err.println(student.getName()); //李四

        String name = "A";
        transferString(name);
        System.err.println(name); //A
    }

    //值传递:
    public void transferValue(int age) {
        age = 20;
    }

    //普通对象传递:
    public void transferReference(Student student) {
        student.setName("李四");
    }

    //String对象传递:(常量池中,各自指向不同的值.)
    public void transferString(String name) {
        name = "B";
    }


}
