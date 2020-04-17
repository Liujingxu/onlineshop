package com.liujx.onlineshop.dao;

import com.liujx.onlineshop.entity.User;

import java.util.List;

/**
 * @author lenovo
 */
public interface UserDao extends DaoForOne, DaoForMany {

    String USER_COLLECTIONS = "user";


    /**
     * S
     * @Description: 找到所有用户
     * @param
     * @return: java.util.List<com.liujx.onlineshop.entity.User>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    List<User> findAllUser();

    /**
     * S
     * @Description: 通过id查找用户
     * @param uid
     * @return: com.liujx.onlineshop.entity.User
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    User findById(String uid);

    /**
     * S
     * @Description: 添加指定用户
     * @param user
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean insertUser(User user);

    /**
     * S
     * @Description: 添加多个用户
     * @param users
     * @return: int
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    int insertUser(List<User> users);

    /**
     * S
     * @Description: 删除用户
     * @param uid
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean deleteUser(String uid);

    /**
     * S
     * @Description: 更新用户
     * @param user
     * @return: com.liujx.onlineshop.entity.User
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    User updateUser(User user);



}
