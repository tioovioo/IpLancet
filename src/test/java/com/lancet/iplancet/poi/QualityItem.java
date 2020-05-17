package com.lancet.iplancet.poi;

import lombok.*;

import java.util.List;

/**
 * @author Jimmy
 * @date 2019/3/8
 * @Description: V3.0_质控项实体类.
 */

@Data
public class QualityItem {

    private String source; //来源
    private String module; //项目
    private String type; //缺陷类型
    private String item; //缺陷内容
    private String maxScore; //分数

    private String unit; //单位

    private String isMergedStatus; //统一子项分值

    private Double max; //扣分上限
    private String enableStatus; //启用/禁用状态
    private Integer level; //层级

    private String sonItem; //4级的缺陷内容
    private String code; //质控编码
    private String pid; //父级质控编码


    private Integer isMerged; //统一子项分值(0-不统一 1-统一)

    private Double deduction; //扣分

    private Integer state; //启用/禁用状态 0-禁用 1-开启


    private Integer updatedBy;//更新人.

    private Integer sameFlag = 0; //3,4级内容一致: 1,不一致:0

    private String vueScore; //前端展示分数.

    private Integer sonIsMergerdFlag; //父级统一子项分值标识. 0-不统一 1-统一

    private List<QualityItem> children; //封装子项.

    private Integer parentIsEnable; //父项是否被禁用.(1-禁用 0-启用)

    private Integer displayType; //缺陷（0）或提醒（1）

    private Integer parentDisplayType; //父项(缺陷或者提醒状态).


    public QualityItem() {
    }

    public QualityItem(String source, String module, String type, String item, Double deduction, String unit, Integer isMerged, Double max, Integer state) {
        this.source = source;
        this.module = module;
        this.type = type;
        this.item = item;
        this.deduction = deduction;
        this.unit = unit;
        this.isMerged = isMerged;
        this.max = max;
        this.state = state;
    }
}
