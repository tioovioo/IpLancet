package com.lancet.iplancet.util;

/**
 * NumberUtil 工具类
 */
public class NumberUtil {

    /**
     *判断Double值是否是整数值
     * @param d
     * @return
     */
    public static Boolean isInteger(Double d){
        if (d.intValue() == d) {
            return true;
        }
        return false;
    }

    /**
     * Integer 类型 null 转换为 0
     * @param i
     * @return
     */
    public static Integer nullToZero(Integer i){
        if (i == null) {
            return 0;
        }
        return i;
    }

}
