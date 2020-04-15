package com.liujx.onlineshop.dao;

import com.liujx.onlineshop.entity.Good;

import java.util.List;

/**
 * @author lenovo
 */
public interface GoodDao extends DaoForOne, DaoForMany {

    String GOODS_COLLECTIONS = "goods";

    List<Good> findAllGood();

    Good findById(String gid);

    boolean insertGood(Good user);

    int insertGood(List<Good> users);

    boolean deleteGood(String gid);

    Good updateGood(Good user);

    List<Good> findByConf(Good good);
}
