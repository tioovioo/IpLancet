package com.lancet.iplancet.poi;

import lombok.Data;

import java.util.Date;

/**
 * @author yongjia.guo
 * @date 2019/6/26
 */

@Data
public class Customer {

    private String id;
    private String cName;
    private String email;
    private String honor;
    private Date time;

    public Customer(String id, String cName, String email, String honor, Date time) {
        this.id = id;
        this.cName = cName;
        this.email = email;
        this.honor = honor;
        this.time = time;
    }
}
