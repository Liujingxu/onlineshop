package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author lenovo
 */
@Service
public class DefaultLoginServiceImpl extends AbstractLoginService {

    @Override
    public boolean match(User user) {
        User userInDao = userDao.findById(user.getUid());
        if (userInDao == null){
            logger.info("ID错误", user.getUid());
            return false;
        }else if (user.getPassword() == null || "".equals(user.getPassword())){
            logger.info("密码输入有误", user.getUid());
            return false;
        }else{
            logger.info("密码验证成功", user.getUid());
            return user.getPassword().equals(userInDao.getPassword());
        }
    }
}
