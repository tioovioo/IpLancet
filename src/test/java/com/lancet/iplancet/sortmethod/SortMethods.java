package com.lancet.iplancet.sortmethod;

/**
 * @author yongjia.guo
 * @Description: 几种常用的排序方法;
 * <p>
 * 当前所有的排序规则均为: 由小到大 (升序) 排列.
 */
public class SortMethods {

    /**
     * 冒泡排序.
     * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int compareCount = 0;

        int temp;
        //优化版:如果外层循环未执行完成,但是当前排序已完成,即可跳出循环.
        boolean flag;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flag = true;
                }
                compareCount++;
            }
            if (!flag) {
                break;
            }
        }
        System.out.println("执行次数:-->   " + compareCount);
        return arr;
    }


    /**
     * 选择排序.
     * 最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)
     *
     * @param arr
     * @return
     */
    public static int[] selctionSort(int[] arr) {

        if (arr.length == 0) {
            return arr;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            //第一个数最小
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {

                //如果比当前最小值小,将最小索引的值赋值为此Min的索引.
                if (arr[minIndex] > arr[j]) {
                    //目的:找出剩余n-i个数中的最小值索引.
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

        }

        return arr;
    }


    /**
     * 插入排序.
     * 最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     *
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            //在有序列表中找到新数的位置.
            for (int j = i + 1; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    //不需要交换.
                    break;
                }
            }
        }

        return arr;
    }

    /**
     * 希尔排序.
     * 最佳情况：T(n) = O(nlog2 n)  最坏情况：T(n) = O(nlog2 n)  平均情况：T(n) =O(nlog2n)
     * <p>
     * 希尔排序也是一种插入排序,它会优先比较距离较远的元素。希尔排序又叫缩小增量排序.
     * <p>
     * 希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；
     * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
     * <p>
     * {n/2,(n/2)/2...1}，称为增量序列,又称为希尔增量，但其实这个增量序列不是最优的.
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {

        if (arr.length == 0) {
            return arr;
        }
        int temp = 0;
        int increase = arr.length;

        //当增量减至 1 时,跳出循环.
        while (increase / 2 > 0) {

            //增量序列规则.
            increase /= 2;

            //根据增量分为若干子序列.
            for (int i = 0; i < increase; i++) {

                for (int j = i + increase; j < arr.length; j += increase) {

                    //序列内部的插入排序比较:
                    for (int k = j; k > i; k -= increase) {
                        if (arr[k] < arr[k - increase]) {
                            temp = arr[k];
                            arr[k] = arr[k - increase];
                            arr[k - increase] = temp;
                        } else {
                            break;
                        }
                    }
                }

            }
        }

        return arr;
    }

    /**
     * 快速排序.
     * 最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)　
     *
     * @param arr
     * @return
     */
    public static int[] quickSort(int[] arr) {


        return  null;
    }


}
