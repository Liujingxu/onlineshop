package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @author lenovo
 */
public interface LoginService {

    String TOKEN = "LOGIN-USER";

    boolean match(User user);

    User login(User user);

    HttpSession signOut(HttpSession session);
}
