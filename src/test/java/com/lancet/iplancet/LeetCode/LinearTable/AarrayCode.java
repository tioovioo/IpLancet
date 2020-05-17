package com.lancet.iplancet.LeetCode.LinearTable;

import org.junit.Test;

import java.util.*;

/**
 * @author yongjia.guo
 * @date 2019/8/29
 * @Description: 数组Code.
 */

public class AarrayCode {
    @Test
    public void testTemplate() {


    }


    /**
     * Titile: Remove Duplicates from Sorted Array.
     * <p>
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * For example, Given input array A = [1,1,2],
     * Your function should return length = 2, and A is now [1,2].
     */
    @Test
    public void test001() {
        int[] arrays = {1, 2, 3, 4, 3, 6};
        int length = removeDuplicates(arrays);
        System.out.println(length);
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[index - 1])
                nums[index++] = nums[i];
        }
        return index;
    }


    /**
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
     * Your algorithm should run in O(n) complexity.
     */
    @Test
    public void test002() {
        int[] arrays = {100, 4, 200, 1, 3, 2, 5, 103, 101, 102, 201, 202};
        int length = longestConsecutive(arrays);
        System.out.println(length);
    }

    private int longestConsecutive(int[] arrays) {

        Map<Integer, Integer> map = new HashMap();

        //从小到大排序:
        for (int i = 0; i < arrays.length; i++) {
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[i] > arrays[j]) {
                    int temp = arrays[i];
                    arrays[i] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }

        boolean flag = false;
        int beginIndex = 0;
        for (int i = 0; i < arrays.length - 1; i++) {
            if (arrays[i] + 1 == arrays[i + 1]) {
                flag = true;
                beginIndex = i;
            }

            if (flag) {
                int count = 2;
                for (int j = i + 1; j < arrays.length - 1; j++) {
                    if (arrays[j] + 1 == arrays[j + 1]) {
                        count++;
                        i++;
                        map.put(beginIndex, count);
                    } else {
                        map.put(beginIndex, count);
                        flag = false;
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arrays));
        Integer result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > result) {
                result = entry.getValue();
            }
        }
        map.forEach((k, v) -> System.out.println("key-> " + k + " value-> " + v));
        return result;
    }


    /**
     * Given an array of integers, find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution.+
     * <p>
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     */
    @Test
    public void test003() {
        int[] numbers = {2, 7, 11, 15};
        int target = 17;
//        int[] result = twoSum(numbers, target);
        int[] result = twoSum3(numbers, target);
        System.out.println(Arrays.toString(result));
    }

    //finalAnswer.
    private int[] twoSum2(int[] nums, int target) {
        final HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            myMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            final Integer v = myMap.get(target - nums[i]);
            if (v != null && v > i) {
                return new int[]{i + 1, v + 1};
            }
        }
        return null;
    }

    //myAnswer.
    private int[] twoSum3(int[] nums, int target) {
        //key->数组元素的值 value->元素的下标
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] arr =new int[2];
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }

        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            Integer index = map.getOrDefault(value, -1);
            if(index != -1){
                arr[0] = i+1;
                arr[1] = index+1;
                return arr;
            }
        }
        return null;
    }


    /**
     Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     Note:
     Elements in a triplet (a,b,c) must be in non-descending order. (ie, a≤b≤c)升序
     The solution set must not contain duplicate triplets.
     For example, given array S = {-1 0 1 2 -1 -4}.
     A solution set is:
     (-1, 0, 1)
     (-1, -1, 2)
     */
    @Test
    public void test004() {
        int[] numbers = {-1,0, 1, 2, -1, -4,-7,-5,7,6};
        Set<List<Integer>> resultSet = threeSumMethod(numbers);
        resultSet.forEach(x-> System.out.println(x));
    }

    private Set<List<Integer>> threeSumMethod(int[] numbers) {
        Set<List<Integer>> resultSet = new HashSet();
        //key->index value->余下两数之和.
        Map<Integer,Integer> tempMap = new HashMap();

        //{-1,0, 1, 2, -1, -4};
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                for (int k = j+1; k < numbers.length; k++) {
                    int sum = numbers[k]+numbers[j]+numbers[i];
                    if(sum == 0){
                        List<Integer> list = new ArrayList();
                        list.add(numbers[i]);
                        list.add(numbers[j]);
                        list.add(numbers[k]);
                        list.sort(Integer::compareTo);
                        resultSet.add(list);
                    }
                }
            }
        }
        return resultSet;
    }





}
