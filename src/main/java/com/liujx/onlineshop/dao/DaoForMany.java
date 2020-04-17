package com.liujx.onlineshop.dao;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.List;

/**
 * @author lenovo
 */
public interface DaoForMany {

    /**
     * S
     * @Description: 查询所有文档
     * @param
     * @return: com.mongodb.client.FindIterable<org.bson.Document>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    FindIterable<Document> findAll();

    /**
     * S
     * @Description: 通过条件查询文档
     * @param condition
     * @return: com.mongodb.client.FindIterable<org.bson.Document>
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    FindIterable<Document> findByCond(Document condition);

    /**
     * S
     * @Description: 添加多个文档
     * @param documents
     * @return: int
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    int insertManyDoc(List<Document> documents);

    /**
     * S
     * @Description: 删除多个文档
     * @param condition
     * @return: int
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    int deleteManyDoc(Document condition);

    /**
     * S
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
