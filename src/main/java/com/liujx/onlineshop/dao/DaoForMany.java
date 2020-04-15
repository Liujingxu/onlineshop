package com.liujx.onlineshop.dao;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.List;

/**
 * @author lenovo
 */
public interface DaoForMany {

    FindIterable<Document> findAll();

    FindIterable<Document> findByCond(Document condition);

    int insertManyDoc(List<Document> documents);

    int deleteManyDoc(Document condition);

    /**
     * @Description:
     * @param condition
     * @param newValue
     * @return: int
     * @Author: Liujx
     * @Date: 2020/4/13
     *
     */
    int updateManyDoc(Document condition, Document newValue);
}
