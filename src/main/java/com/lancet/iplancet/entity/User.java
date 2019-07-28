package com.lancet.iplancet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: yongjia.guo
 * Create Date : 2019/7/13.
 */

@Data
@NoArgsConstructor
public class User {

    private Integer userId;
    private String userName; //用户姓名
    private String userEmail; //用户邮箱
    private Integer bankId; //银行卡号
    private Integer telephoneNumber; //联系电话
    private Integer userRight; //用户权限(0- 管理员 1-普通用户)
//    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8" )
    private Date createTime; //创建时间
}
