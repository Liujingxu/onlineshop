package com.liujx.onlineshop.dao;
import com.liujx.onlineshop.entity.BuyDoc;

import com.liujx.onlineshop.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    void findAllUser() {
        List<User> user = userDao.findAllUser();
        String name = user.get(0).getName();
//        Assert.assertEquals("刘晶旭", name);
    }

    @Test
    void findById() {
        User user = userDao.findById("u0001");
//        Assert.assertEquals("15603417563", user.getTel());
    }

    @Test
    void insertUser() {
        User user = new User();
        user.setUid("u0001");
        user.setPassword("123456");
        user.setName("刘晶旭");
        user.setTel("15603417563");
        user.setAddress("山西");
        user.setBuyCars(new BuyDoc[0]);
        user.setHistoryList(new BuyDoc[0]);

//        userDao.insertUser(user);
    }

    @Test
    void insertUser1() {

    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}