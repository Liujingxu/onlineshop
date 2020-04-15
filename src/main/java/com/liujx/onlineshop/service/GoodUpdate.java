package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.Good;

import java.util.List;

/**
 * @author lenovo
 */
public interface GoodUpdate {

    Long addClick(String gid);

    Good updateGoods(String gid, Integer number);

    List<Good> addGoods(Good good);

}
