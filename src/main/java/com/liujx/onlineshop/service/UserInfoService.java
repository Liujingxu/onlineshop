package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.User;

/**
 * @author lenovo
 */
public interface UserInfoService {

    /**
     * S
     * @Description: 通过id查找用户
     * @param uid
     * @return: com.liujx.onlineshop.entity.User
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    User getUserById(String uid);

    /**
     * S
     * @Description: 更新指定对象
     * @param user
     * @return: com.liujx.onlineshop.entity.User
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    User updateUser(User user);

    /**
     * S
     * @Description: 删除指定用户
     * @param uid
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean deleteUser(String uid);

    /**
     * S
     * @Description: 添加用户
     * @param user
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean addUser(User user);

}
