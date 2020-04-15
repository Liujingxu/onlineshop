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
public class User {

    private String uid;

    private String password;

    private String name;

    private String avatar;

    private String tel;

    private String address;

    private BuyDoc[] buyCars;

    private BuyDoc[] historyList;

    public List<BuyDoc> getBuyCarsByList() {
        return Arrays.asList(buyCars);
    }

    public List<BuyDoc> getHistoryListByList() {
        return Arrays.asList(historyList);
    }

    public User(String uid, String password, String name) {
        this.uid = uid;
        this.password = password;
        this.name = name;
    }
}
