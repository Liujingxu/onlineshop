package com.liujx.onlineshop.service;

import com.liujx.onlineshop.dao.GoodDao;
import com.liujx.onlineshop.dao.Logger;
import com.liujx.onlineshop.entity.Good;
import com.liujx.onlineshop.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lenovo
 */
public abstract class AbstractGoodsQuery implements GoodsQuery {

    @Autowired
    protected GoodDao goodDao;

    @Autowired
    protected Logger logger;

    @Override
    public List<Good> getAllGoods(Integer number) {
        logger.info("访问数据库:获取所有商品信息", Log.SYSTEM);
        List<Good> allGood = goodDao.findAllGood();
        List<Good> result = new ArrayList<>();
        if (number <= 0){
            result = allGood;
        }else {
            logger.info("过滤数据集", Log.SYSTEM);
            for (int i = 0; i < number; i++){
                result.add(allGood.get(i));
            }
        }

        logger.info("返回符合结果数据集", Log.SYSTEM);
        return sort(result);
    }

    @Override
    public List<Good> getGoodsByName(String name) {
        Good good = new Good();
        good.setName(name);
        logger.info("访问数据库:根据name查询商品数据", Log.SYSTEM);
        List<Good> goods = goodDao.findByConf(good);
        logger.info("返回符合结果数据集", Log.SYSTEM);
        return sort(goods);
    }

    @Override
    public Good getGoodsById(String gid) {
        logger.info("访问数据库:根据id查询商品数据", Log.SYSTEM);
        return goodDao.findById(gid);
    }

    protected abstract List<Good> sort(List<Good> goods);
}
