package com.liujx.onlineshop.service;

import com.liujx.onlineshop.dao.GoodDao;
import com.liujx.onlineshop.entity.Evaluate;
import com.liujx.onlineshop.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: onlineshop
 * @description:
 * @author: Liujx
 * @create: 2020-04-15 21:40
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private GoodDao goodDao;

    @Override
    public boolean addComment(String gid, Evaluate evaluate) {
        Good good = goodDao.findById(gid);
        List<Evaluate> list = good.getEvaluatesByList();
        if (list == null){
            list = new ArrayList<>();
        }
        ArrayList<Evaluate> evaluates = new ArrayList<>(list);
        evaluates.add(evaluate);
        good.setEvaluates(evaluates.toArray(new Evaluate[0]));
        goodDao.updateGood(good);
        return true;
    }
}
