package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.User;

/**
 * @author lenovo
 */
public interface UserInfoService {

    User getUserById(String uid);

    User updateUser(User user);

    boolean deleteUser(String uid);

    boolean addUser(User user);

}
