package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.Good;

import java.util.List;


/**
 * @author lenovo
 */
public interface GoodsQuery {

    List<Good> getAllGoods(Integer number);

    List<Good> getGoodsByName(String name);

    Good getGoodsById(String gid);


}
