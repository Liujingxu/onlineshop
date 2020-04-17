package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.BuyDoc;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lenovo
 */
public interface ShoppingCar {

    /**
     * S
     * @Description: 将一条商品记录添加入购物车
     * @param session
     * @param buyDoc
     * @return: java.util.List<com.liujx.onlineshop.entity.BuyDoc>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    List<BuyDoc> addCar(HttpSession session, BuyDoc buyDoc);

    /**
     * S
     * @Description: 查看购物车
     * @param session
     * @return: java.util.List<com.liujx.onlineshop.entity.BuyDoc>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    List<BuyDoc> checkCar(HttpSession session);

    /**
     * S
     * @Description: 根据gid删除购物车记录
     * @param session
     * @param gid
     * @return: java.util.List<com.liujx.onlineshop.entity.BuyDoc>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    List<BuyDoc> deleteCar(HttpSession session, String gid);

    /**
     * S
     * @Description: 结算
     * @param session
     * @return: java.lang.Integer
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Integer submit(HttpSession session);
}
