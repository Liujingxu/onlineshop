package com.liujx.onlineshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lenovo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    public static final String TIMESTAMP = "timestamp";
    public static final String UID = "uid";
    public static final String BEHAVIOR = "behavior";
    public static final String SYSTEM = "SYSTEM";
    public static final String SYSTEM_PRE = "X";

    private Long timestamp;

    private String uid;

    private String behavior;
}
