package com.liujx.onlineshop.service;

import com.liujx.onlineshop.dao.GoodDao;
import com.liujx.onlineshop.dao.UserDao;
import com.liujx.onlineshop.entity.BuyDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @program: onlineshop
 * @description: this is an abstract class to impl the class BackLogic
 * @author: Liujx
 * @create: 2020-04-13 22:32
 **/
public abstract class AbstractBackLogic implements BackLogic{


    @Autowired
    protected UserDao userDao;

    @Override
    public Float calAllList(String uid) {
        List<BuyDoc> cars = userDao.findById(uid).getBuyCarsByList();
        Float sum = 0f;
        for (BuyDoc bd : cars){
            sum  = sum + cal(bd);
        }
        return sum;
    }

    @Override
    public Float calculateItem(BuyDoc buyDoc) {
        return cal(buyDoc);
    }

    @Override
    public Map<String, Object> calUserHistory(String uid) {
        List<BuyDoc> historyList = userDao.findById(uid).getHistoryListByList();
        List<Date> dates = new ArrayList<>();
        List<Float> price = new ArrayList<>();

        historyList = filter(historyList);

        for (BuyDoc bd : historyList){
            Float result = cal(bd);
            dates.add(bd.getTime());
            price.add(result);
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("dates", dates);
        map.put("price", price);
        return map;
    }


    protected abstract Float cal(BuyDoc buyDoc);
    protected abstract List<BuyDoc> filter(List<BuyDoc> alls);
}
