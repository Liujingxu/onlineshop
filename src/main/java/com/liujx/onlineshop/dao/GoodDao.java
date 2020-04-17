package com.liujx.onlineshop.dao;

import com.liujx.onlineshop.entity.Good;

import java.util.List;

/**
 * @author lenovo
 */
public interface GoodDao extends DaoForOne, DaoForMany {

    String GOODS_COLLECTIONS = "goods";

    /**
     * S
     * @Description: 找到所有商品
     * @param
     * @return: java.util.List<com.liujx.onlineshop.entity.Good>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    List<Good> findAllGood();

    /**
     * S
     * @Description: 通过id找到指定商品
     * @param gid
     * @return: com.liujx.onlineshop.entity.Good
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Good findById(String gid);

    /**
     * S
     * @Description: 添加商品
     * @param good
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean insertGood(Good good);

    /**
     * S
     * @Description: 添加多个商品
     * @param goods
     * @return: int
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    int insertGood(List<Good> goods);

    /**
     * S
     * @Description: 删除商品
     * @param gid
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean deleteGood(String gid);

    /**
     * S
     * @Description: 更新商品信息
     * @param good
     * @return: com.liujx.onlineshop.entity.Good
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Good updateGood(Good good);

    /**
     * S
     * @Description: 通过条件寻找商品
     * @param good
     * @return: java.util.List<com.liujx.onlineshop.entity.Good>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    List<Good> findByConf(Good good);
}
