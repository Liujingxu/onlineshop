package com.liujx.onlineshop.dao;
import com.liujx.onlineshop.entity.Evaluate;

import com.liujx.onlineshop.entity.Good;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GoodDaoImplTest {

    @Autowired
    private GoodDao goodDao = new GoodDaoImpl();

    @Test
    void findAllGood() {
        List<Good> list = goodDao.findAllGood();
        Good good = list.get(0);
        Assert.assertEquals("机器学习", good.getName());
    }

    @Test
    void findById() {
        Good good = goodDao.findById("g0001");
        Assert.assertEquals(20, good.getClick());
    }

    @Test
    void insertGood() {
        Good good = new Good();
        good.setGid("g0001");
        good.setName("机器学习");
        good.setPrice(88.0F);
        good.setNumber(1000L);
        good.setClick(20L);
        good.setEvaluates(new Evaluate[]{new Evaluate("e1", 5, "好评", "u0001")});
        goodDao.insertGood(good);
    }

    @Test
    void insertGood1() {
    }

    @Test
    void deleteGood() {
    }

    @Test
    void updateGood() {
    }
}