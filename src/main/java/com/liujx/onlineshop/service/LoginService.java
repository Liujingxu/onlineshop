package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @author lenovo
 */
public interface LoginService {

    String TOKEN = "LOGIN-USER";

    /**
     * S
     * @Description: 匹配用户信息
     * @param user
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean match(User user);

    /**
     * S
     * @Description: 登录
     * @param user
     * @return: com.liujx.onlineshop.entity.User
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    User login(User user);

    /**
     * S
     * @Description: 登出
     * @param session
     * @return: javax.servlet.http.HttpSession
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    HttpSession signOut(HttpSession session);
}
