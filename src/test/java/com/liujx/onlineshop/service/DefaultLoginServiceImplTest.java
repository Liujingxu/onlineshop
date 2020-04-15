package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class DefaultLoginServiceImplTest {

    @Autowired
    private LoginService loginService;

    @Test
    void match() {
        User user = new User("u0001", "123456", null, null, null, null, null, null);
        User login = loginService.login(user);
        Assert.assertEquals("刘晶旭", login.getName());
    }
}