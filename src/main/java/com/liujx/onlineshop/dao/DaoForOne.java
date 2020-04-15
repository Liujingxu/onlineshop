package com.liujx.onlineshop.dao;

import org.bson.Document;

import java.util.List;

/**
 * @author lenovo
 */
public interface DaoForOne {


    Document findDocById(String id);

    boolean insertDoc(Document document);

    boolean deleteDoc(String id);

    Document updateDoc(Document document);
}
