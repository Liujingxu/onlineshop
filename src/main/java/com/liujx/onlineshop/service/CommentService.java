package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.Evaluate;

/**
 * @author lenovo
 */
public interface CommentService {

    boolean addComment(String gid, Evaluate evaluate);

}
