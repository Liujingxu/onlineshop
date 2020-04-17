package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.Good;

import java.util.List;


/**
 * @author lenovo
 */
public interface GoodsQuery {

    /**
     * S
     * @Description: 获取所有商品
     * @param number
     * @return: java.util.List<com.liujx.onlineshop.entity.Good>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    List<Good> getAllGoods(Integer number);

    /**
     * S
     * @Description: 通过name查询商品
     * @param name
     * @return: java.util.List<com.liujx.onlineshop.entity.Good>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    List<Good> getGoodsByName(String name);

    /**
     * S
     * @Description: 通过id查询商品
     * @param gid
     * @return: com.liujx.onlineshop.entity.Good
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Good getGoodsById(String gid);


}
