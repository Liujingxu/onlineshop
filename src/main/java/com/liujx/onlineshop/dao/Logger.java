package com.liujx.onlineshop.dao;

import com.liujx.onlineshop.entity.Log;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author lenovo
 */
@Repository
public class Logger implements DaoForOne {

    @Autowired
    private MongoDatabase mongoDatabase;

    private MongoCollection<Document> getCollection(){
        return mongoDatabase.getCollection("logs");
    }

    public void info(String msg, String uid){
        Document document = new Document();
        document.append(Log.TIMESTAMP, System.currentTimeMillis());
        document.append(Log.UID, uid);
        document.append(Log.BEHAVIOR, msg);
        // 这地方暂时这么写
        System.out.println(document.toString());
        insertDoc(document);
    }

    @Override
    public Document findDocById(String id) {
        return null;
    }

    @Override
    public boolean insertDoc(Document document) {
        MongoCollection<Document> collection = getCollection();
        collection.insertOne(document);
        return true;
    }

    @Override
    public boolean deleteDoc(String id) {
        return false;
    }

    @Override
    public Document updateDoc(Document document) {
        return null;
    }
}
