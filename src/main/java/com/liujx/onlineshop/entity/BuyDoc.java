package com.liujx.onlineshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lenovo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyDoc {

    private String uid;

    private String gid;

    private Date time;

    private Integer number;

}
