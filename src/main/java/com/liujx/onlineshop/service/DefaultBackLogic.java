package com.liujx.onlineshop.service;

import com.liujx.onlineshop.dao.GoodDao;
import com.liujx.onlineshop.entity.BuyDoc;
import com.liujx.onlineshop.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: onlineshop
 * @description: this is son class from AbstractBackLogic
 * @author: Liujx
 * @create: 2020-04-13 22:47
 **/
@Service
public class DefaultBackLogic extends AbstractBackLogic{

    @Autowired
    protected GoodDao goodDao;

    @Override
    protected Float cal(BuyDoc buyDoc) {
        Good good = goodDao.findById(buyDoc.getGid());
        return good.getPrice() * buyDoc.getNumber();
    }

    @Override
    protected List<BuyDoc> filter(List<BuyDoc> alls) {
        return alls;
    }
}
