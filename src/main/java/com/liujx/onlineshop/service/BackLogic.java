package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.BuyDoc;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 */
public interface BackLogic {

    // TODO 这儿咋没用了直接就算了
    /**
     * S
     * @Description: 计算一条记录的价格
     * @param buyDoc
     * @return: java.lang.Float
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Float calculateItem(BuyDoc buyDoc);

    /**
     * S
     * @Description: 计算购物车的所有商品价格
     * @param uid
     * @return: java.lang.Float
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Float calAllList(String uid);

    /**
     * S
     * @Description: 得到可视化数据
     * @param uid
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Map<String, Object> calUserHistory(String uid);
}
