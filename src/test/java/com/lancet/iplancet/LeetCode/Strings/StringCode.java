package com.lancet.iplancet.LeetCode.Strings;

import org.junit.Test;

/**
 * @author yongjia.guo
 * @date 2019/8/29
 * @Description:
 */
public class StringCode {

    @Test
    public void test001() {
       String a = "111";
       String b = "1";
       String c = addBinary(a,b);
       System.out.println(c);
    }


    private String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while(i >= 0 || j >= 0 || carry > 0) {
            int valueA = i < 0 ? 0 : a.charAt(i--) - '0';
            int valueB = j < 0 ? 0 : b.charAt(j--) - '0';
            int sum = valueA + valueB + carry;
            result.insert(0, Character.forDigit(sum % 2, 10));
            carry = sum / 2;
        }
        return result.toString();
    }
}
