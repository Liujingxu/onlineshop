package com.liujx.onlineshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author lenovo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {

    private String gid;

    private String name;

    private String imageUrl;

    private float price;

    private long number;

    private long click;

    private Evaluate[] evaluates;

    public List<Evaluate> getEvaluatesByList() {
        return Arrays.asList(this.evaluates);
    }


}
