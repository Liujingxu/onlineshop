package com.liujx.onlineshop.service;

import com.liujx.onlineshop.dao.Logger;
import com.liujx.onlineshop.dao.UserDao;
import com.liujx.onlineshop.entity.Log;
import com.liujx.onlineshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * @author lenovo
 */
public abstract class AbstractLoginService implements LoginService {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected Logger logger;

    private User user = new User(String.valueOf(System.currentTimeMillis()), null, "]游客", null, null, null, null, null);

    @Override
    public User login(User user) {
        if (user == null){
            logger.info("X输入有误", Log.SYSTEM);
            return null;
        }
        logger.info("用户登录中...", Log.SYSTEM);
        if (match(user)){
            this.user = userDao.findById(user.getUid());
            logger.info("登录成功", user.getUid());
        }
        return this.user;
    }

    @Override
    public HttpSession signOut(HttpSession session) {
        String token = (String) session.getAttribute(TOKEN);
        if (token == null){
            logger.info("X用户登出错误", Log.SYSTEM);
            return session;
        }else if (token.equals(this.user.getUid())){
            session.removeAttribute(TOKEN);
            logger.info("用户登出成功", token);
            return session;
        }else{
            logger.info("用户登出失败", token);
            return session;
        }
    }
}
