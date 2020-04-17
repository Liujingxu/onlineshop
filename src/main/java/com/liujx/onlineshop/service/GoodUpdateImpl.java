package com.liujx.onlineshop.service;

import com.liujx.onlineshop.dao.GoodDao;
import com.liujx.onlineshop.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: onlineshop
 * @description:
 * @author: Liujx
 * @create: 2020-04-13 22:54
 **/
@Service
public class GoodUpdateImpl implements GoodUpdate {

    @Autowired
    private GoodDao goodDao;

    @Override
    public Long addClick(String gid) {
        Good good = goodDao.findById(gid);
        good.setClick(good.getClick() + 1);
        Good good1 = goodDao.updateGood(good);
        return good1.getClick();
    }

    /**
     * @Description: 更新用户库存操作
     * @param gid 商品号
     * @param number 传入参数正为添加，负为减少
     * @return: com.liujx.onlineshop.entity.Good
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    @Override
    public Good updateGoods(String gid, Integer number) {
        Good good = goodDao.findById(gid);
        good.setNumber(good.getNumber() + number);
        return goodDao.updateGood(good);
    }

    @Override
    public List<Good> addGoods(Good good) {
        List<Good> allGood = null;
        if (goodDao.insertGood(good)){
            allGood = goodDao.findAllGood();
        }
        return allGood;
    }
}
