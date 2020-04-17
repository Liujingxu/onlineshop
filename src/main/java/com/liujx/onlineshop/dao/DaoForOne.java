package com.liujx.onlineshop.dao;

import org.bson.Document;

import java.util.List;

/**
 * @author lenovo
 */
public interface DaoForOne {


    /**
     * S
     * @Description: 通过id找文档
     * @param id
     * @return: org.bson.Document
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Document findDocById(String id);

    /**
     * S
     * @Description: 添加指定文档
     * @param document
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean insertDoc(Document document);

    /**
     * S
     * @Description: 删除指定文档
     * @param id
     * @return: boolean
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    boolean deleteDoc(String id);

    /**
     * S
     * @Description: 修改指定文档
     * @param document
     * @return: org.bson.Document
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    Document updateDoc(Document document);
}
