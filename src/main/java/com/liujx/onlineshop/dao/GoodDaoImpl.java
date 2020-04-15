package com.liujx.onlineshop.dao;

import com.liujx.onlineshop.entity.Evaluate;
import com.liujx.onlineshop.entity.Good;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lenovo
 */
@Repository
public class GoodDaoImpl implements GoodDao {

    @Autowired
    private MongoDatabase mongoDatabase;


    private final String gid = "gid";
    private final String gname = "name";
    private final String gimageUrl = "imageUrl";
    private final String gprice = "price";
    private final String gnumber = "number";
    private final String gclick = "click";
    private final String evaluates = "evaluates";
    private final String eid = "eid";
    private final String level = "level";
    private final String context = "context";
    private final String uid = "uid";



    @Override
    public List<Good> findAllGood() {
        List<Good> goods = new ArrayList<>();
        for (Document doc : findAll()){
            goods.add(trans(doc));
        }
        return goods;
    }

    @Override
    public Good findById(String gid) {
        return trans(findDocById(gid));
    }

    @Override
    public boolean insertGood(Good good) {
        return insertDoc(trans(good));
    }

    @Override
    public int insertGood(List<Good> goods) {
        return 0;
    }

    @Override
    public boolean deleteGood(String gid) {
        return deleteDoc(gid);
    }

    @Override
    public Good updateGood(Good good) {
        Document document = updateDoc(trans(good));
        return trans(document);
    }

    @Override
    public List<Good> findByConf(Good good) {
        if (good == null){
            return null;
        }
        List<Good> goods = new ArrayList<>();
        Document document = selectCondition(good);
        FindIterable<Document> cond = findByCond(document);
        for (Document doc : cond){
            goods.add(trans(doc));
        }

        return goods;
    }

    private Document selectCondition(Good good){
        Document document = new Document();
        document.append(gname, good.getName());
        return document;
    }

    @Override
    public FindIterable<Document> findAll() {
        MongoCollection<Document> collection = getCollection();
        return collection.find();
    }

    @Override
    public FindIterable<Document> findByCond(Document condition) {
        if (condition == null){
            return null;
        }

        MongoCollection<Document> collection = getCollection();

        Set<Map.Entry<String, Object>> entrySet = condition.entrySet();
        Bson[] bsons = new Bson[entrySet.size()];
        int i = 0;
        for (Map.Entry entry : entrySet){
            String key = (String) entry.getKey();
            Bson bson = Filters.regex(key, (String) entry.getValue());
            bsons[i] = bson;
        }
        Bson and = Filters.and(bsons);
        return collection.find(and);
    }

    @Override
    public int insertManyDoc(List<Document> documents) {
        MongoCollection<Document> collection = getCollection();
        collection.insertMany(documents);
        return documents.size();
    }

    @Override
    public int deleteManyDoc(Document condition) {
        return 0;
    }

    @Override
    public int updateManyDoc(Document condition, Document newValue) {
        return 0;
    }

    @Override
    public Document findDocById(String id) {
        MongoCollection<Document> collection = getCollection();
        FindIterable<Document> iterable = collection.find(Filters.eq(gid, id));
        return iterable.cursor().next();
    }

    @Override
    public boolean insertDoc(Document document) {
        MongoCollection<Document> collection = getCollection();
        collection.insertOne(document);
        return true;
    }

    @Override
    public boolean deleteDoc(String id) {
        MongoCollection<Document> collection = getCollection();
        DeleteResult result = collection.deleteOne(Filters.eq(gid, id));
        return result.getDeletedCount() == 1;
    }

    @Override
    public Document updateDoc(Document document) {
        MongoCollection<Document> collection = getCollection();
        Bson bson = Filters.eq(gid, document.getString(gid));
        UpdateResult result = collection.replaceOne(bson, document);
        return collection.find(bson).cursor().next();
    }

    private MongoCollection<Document> getCollection(){
        return mongoDatabase.getCollection(GOODS_COLLECTIONS);
    }

    private Good trans(Document next){
        Good good = new Good();
        if (next.containsKey(gid)){
            good.setGid(next.getString(gid));
        }

        if (next.containsKey(gname)){
            good.setName(next.getString(gname));
        }
        if (next.containsKey(gimageUrl)){
            good.setImageUrl(next.getString(gimageUrl));
        }
        if (next.containsKey(gprice)){
            good.setPrice(next.getDouble(gprice).floatValue());
        }

        if (next.containsKey(gnumber)){
            good.setNumber(next.getLong(gnumber));
        }

        if (next.containsKey(gclick)){
            good.setClick(next.getLong(gclick));
        }

        if (next.containsKey(evaluates)){
            List<Document> evaluates = next.getList(this.evaluates, Document.class);
            Evaluate[] evaluatess = new Evaluate[evaluates.size()];
            int i = 0;
            for (Document doc : evaluates){
                Evaluate evaluate = new Evaluate();
                if (doc.containsKey(eid)) {
                    evaluate.setEid(doc.getString(eid));
                }
                if (doc.containsKey(level)) {
                    evaluate.setLevel(doc.getInteger(level));
                }
                if (doc.containsKey(context)) {
                    evaluate.setContext(doc.getString(context));
                }
                if (doc.containsKey(uid)) {
                    evaluate.setUid(doc.getString(uid));
                }
                evaluatess[i++] = evaluate;
            }
            good.setEvaluates(evaluatess);
        }
        return good;
    }

    private Document trans(Good good){
        Document document = new Document();
        document.append(gid, good.getGid());
        document.append(gname, good.getName());
        document.append(gimageUrl, good.getImageUrl());
        document.append(gprice, good.getPrice());
        document.append(gnumber, good.getNumber());
        document.append(gclick, good.getClick());
        List<Document> evaluates = new ArrayList<>();
        for (Evaluate eva: good.getEvaluates()){
            Document document1 = new Document();
            document1.append(eid, eva.getEid());
            document1.append(level, eva.getLevel());
            document1.append(context, eva.getContext());
            document1.append(uid, eva.getUid());
            evaluates.add(document1);
        }
        document.append(this.evaluates, evaluates);


        return document;
    }
}
