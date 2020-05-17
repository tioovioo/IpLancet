package com.lancet.iplancet.sortmethod;

import com.lancet.iplancet.util.NumberUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author yongjia.guo
 * @Description: 排序方法测试类.
 */
public class SortTest {

    @Test
    public void templateMethod() {

    }


    //冒泡:
    @Test
    public void bubbleSortMethod() {
        int arr[] = {100, 50, 32, 21, 19, 13, 7, 5, 4, 1, 73, 92};

        int[] ints = SortMethods.bubbleSort(arr);

        System.out.println(Arrays.toString(ints));

    }

    //选择:
    @Test
    public void selctionSortMethod() {
        int arr[] = {100, 50, 32, 21, 19, 13, 7, 5, 4, 1, 73, 92};
        int[] ints = SortMethods.selctionSort(arr);

        System.out.println(Arrays.toString(ints));

    }

    //插入:
    @Test
    public void insertionSortMethod() {
        int arr[] = {100, 50, 32, 21, 19, 13, 7, 5, 4, 1, 73, 92};
        int[] ints = SortMethods.insertionSort(arr);

        System.out.println(Arrays.toString(ints));

    }

    //希尔:
    @Test
    public void shellSortMethod() {
        int arr[] = {100, 50, 32, 21, 19, 13, 7, 5, 4, 1, 73, 92};
        int[] ints = SortMethods.shellSort(arr);

        System.out.println(Arrays.toString(ints));

    }


    @Test
    public void tt01() {
        int gap = 8;
        int count = 0;
        while (gap > 0) {
            count++;
            gap /= 2;
            System.out.println("当前gap值为:  " + gap);
        }
        System.out.println("循环次数: " + count);

    }


    @Test
    public void sortTest() {
        int arr[] = {1, 4, 6, 3, 5, 89, 100, 43, 21, 42};
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));

    }


    public long aFunc(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return aFunc(n - 1) + aFunc(n - 2);
        }
    }

    @Test
    public void timeMethod() {
        long begin = System.currentTimeMillis();
        int n = 50;
        aFunc(n);

        System.out.println("当前数量为: " + n + " ,共耗时: " + (System.currentTimeMillis() - begin) + " 毫秒");

    }

    @Test
    public void recursiveTest() {

//        t012(19, 1);
        long begin = System.currentTimeMillis();
        factorialMethod(20, 1);
        System.out.println("本次计算共耗时:" + (System.currentTimeMillis() - begin) + " 毫秒.");

    }

    @Test
    public void t021() {
        sumFactorialMethod(20);
        //1!+2!+3!+4!+5!+6!+7!+8!+9!+10!+11!+12!+13!+14!+15!+16!+17!+18!+19!+20!=2561327494111820314

    }


    private void t012(int n, int i) {
        if (n < 0) {
            System.out.println("跳出======");
        } else {
            System.out.println("第: " + i + " 次执行,当前n值为: " + n);
            i++;
            t012(n - 2, i);
        }

    }

    public void factorialMethod(int n, double result) {
        if (n == 1) {
            System.out.println("==跳出==");
            System.out.println(n + "的阶乘计算结果为: " + result);
        } else {
            n -= 1;
            result *= n;
            System.out.println("当前n值为: " + n + " ;result值为: " + result);
            factorialMethod(n, result);
        }


    }

    public long sumFactorialMethod(int n) {
        long sum =1,num =1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(i+"!");
             num *= i;
             sum += num;
             if(i<n){
                 sb.append("+");
             }
        }
        System.out.println(sb.toString()+"="+sum);
        return sum;
    }





}
