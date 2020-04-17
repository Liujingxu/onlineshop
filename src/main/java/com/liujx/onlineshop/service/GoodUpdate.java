package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.Good;

import java.util.List;

/**
 * @author lenovo
 */
public interface GoodUpdate {

    /**
     * S
     * @Description: 添加点击量
     * @param gid
     * @return: java.lang.Long
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Long addClick(String gid);

    /**
     * S
     * @Description: 更新商品
     * @param gid
     * @param number
     * @return: com.liujx.onlineshop.entity.Good
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Good updateGoods(String gid, Integer number);

    /**
     * S
     * @Description: 添加商品
     * @param good
     * @return: java.util.List<com.liujx.onlineshop.entity.Good>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    List<Good> addGoods(Good good);

}
