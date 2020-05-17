package com.interview.JUC.Special;

import lombok.Getter;

/**
 * @author: yongjia.guo
 * Create Date : 2020/5/16.
 * 枚举类->相当于数据库
 * CountryEnum->数据库名称
 * ONE-> 表名称
 * 1/楚国-> 字段对应的值
 * <p>
 * 枚举默认有set方法.
 */
public enum CountryEnum {
    ONE(1, "楚国"), TWO(2, "赵国"), THREE(3, "齐国"), FOUR(4, "韩国"), FIVE(5, "魏国"), SIX(6, "燕国");

    @Getter
    private Integer sortNumber;
    @Getter
    private String countryName;

    CountryEnum(Integer sortNumber, String countryName) {
        this.sortNumber = sortNumber;
        this.countryName = countryName;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum element : countryEnums) {
            if (index == element.getSortNumber()) {
                return element;
            }
        }
        return null;
    }
}
