package com.liujx.onlineshop.dao;

import com.liujx.onlineshop.entity.User;

import java.util.List;

/**
 * @author lenovo
 */
public interface UserDao extends DaoForOne, DaoForMany {

    String USER_COLLECTIONS = "user";

    List<User> findAllUser();

    User findById(String uid);

    boolean insertUser(User user);

    int insertUser(List<User> users);

    boolean deleteUser(String uid);

    User updateUser(User user);



}
