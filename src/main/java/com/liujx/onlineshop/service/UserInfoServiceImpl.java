package com.liujx.onlineshop.service;

import com.liujx.onlineshop.dao.Logger;
import com.liujx.onlineshop.dao.UserDao;
import com.liujx.onlineshop.entity.BuyDoc;
import com.liujx.onlineshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lenovo
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Logger logger;


    @Override
    public User getUserById(String uid) {
        logger.info("访问数据库:获取用户信息", uid);
        return userDao.findById(uid);
    }

    @Override
    public User updateUser(User user) {
        logger.info("访问数据库:修改用户信息", user.getUid());
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(String uid) {
        logger.info("访问数据库:删除用户", uid);
        return userDao.deleteUser(uid);
    }

    @Override
    public boolean addUser(User user) {
        logger.info("访问数据库:添加用户", user.getUid());
        user.setBuyCars(new BuyDoc[0]);
        user.setHistoryList(new BuyDoc[0]);
        return userDao.insertUser(user);
    }
}
