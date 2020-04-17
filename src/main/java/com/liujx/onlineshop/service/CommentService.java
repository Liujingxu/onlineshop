package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.Evaluate;

/**
 * @author lenovo
 */
public interface CommentService {

    /**
     * S
     * @Description: 添加一条评论
     * @param gid
     * @param evaluate
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean addComment(String gid, Evaluate evaluate);

}
