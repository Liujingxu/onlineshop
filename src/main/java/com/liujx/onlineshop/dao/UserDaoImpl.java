package com.liujx.onlineshop.dao;

import com.liujx.onlineshop.entity.BuyDoc;
import com.liujx.onlineshop.entity.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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

/**
 * @author lenovo
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoDatabase mongoDatabase;

    private final String uid = "uid";
    private final String password = "password";
    private final String name = "name";
    private final String avatar = "avatar";
    private final String tel = "tel";
    private final String address = "address";
    private final String gbuyCars = "buyCars";
    private final String gid = "gid";
    private final String time = "time";
    private final String number = "number";
    private String ghistoryList = "historyList";


    @Override
    public List<User> findAllUser() {
        FindIterable<Document> all = findAll();
        MongoCursor<Document> cursor = all.cursor();
        List<User> users = new ArrayList<>();
        while (cursor.hasNext()){
            Document next = cursor.next();
            User user = trans(next);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findById(String uid) {
        Document doc = findDocById(uid);
        return doc != null ? trans(doc) : null;
    }

    @Override
    public boolean insertUser(User user) {
        Document document = trans(user);
        return insertDoc(document);
    }

    @Override
    public int insertUser(List<User> users) {
        List<Document> documents = new ArrayList<>();
        for (User user: users){
            documents.add(trans(user));
        }
        return insertManyDoc(documents);
    }

    @Override
    public boolean deleteUser(String uid) {
        return deleteDoc(uid);
    }

    @Override
    public User updateUser(User user) {
        Document document = updateDoc(trans(user));
        return trans(document);
    }


    @Override
    public FindIterable<Document> findAll() {
        MongoCollection<Document> collection = getCollection();
        return collection.find();
    }

    @Override
    public FindIterable<Document> findByCond(Document condition) {
        return null;
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
        FindIterable<Document> documents = collection.find(Filters.eq(uid, id));
        return documents.cursor().hasNext()? documents.cursor().next() : null;
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
        DeleteResult result = collection.deleteOne(Filters.eq(uid, id));
        return result.getDeletedCount() == 1;
    }

    @Override
    public Document updateDoc(Document document) {
        MongoCollection<Document> collection = getCollection();
        Bson bson = Filters.eq(uid, document.get(uid));
        UpdateResult updateResult = collection.replaceOne(bson, document);
        FindIterable<Document> iterable = collection.find(bson);
        return iterable.cursor().next();
    }

    private MongoCollection<Document> getCollection(){
        return mongoDatabase.getCollection(USER_COLLECTIONS);
    }

    /**
     * @Description: 将doc转为user
     * @param next
     * @return: com.liujx.onlineshop.entity.User
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    private User trans(Document next){
        User user = new User();
        if (next.containsKey(uid)){
            user.setUid(next.getString(uid));
        }

        if (next.containsKey(password)){
            user.setPassword(next.getString(password));
        }
        if (next.containsKey(name)){
            user.setName(next.getString(name));
        }

        if (next.containsKey(avatar)){
            user.setAvatar(next.getString(avatar));
        }

        if (next.containsKey(tel)){
            user.setTel(next.getString(tel));
        }

        if (next.containsKey(address)){
            user.setAddress(next.getString(address));
        }
        List<Document> temp = null;
        if (next.containsKey(gbuyCars)){
            temp = next.getList(gbuyCars, Document.class);
            BuyDoc[] buyDocs = toArray(temp);
            user.setBuyCars(buyDocs);
        }

        if (next.containsKey(ghistoryList)){
            temp = next.getList(ghistoryList, Document.class);
            BuyDoc[] buyDocs = toArray(temp);
            user.setHistoryList(buyDocs);
        }
        return user;
    }

    /**
     * @Description: 将doc集合转为bd数组
     * @param temp
     * @return: com.liujx.onlineshop.entity.BuyDoc[]
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    private BuyDoc[] toArray(List<Document> temp){

        BuyDoc[] buyDocs = new BuyDoc[temp.size()];
        int i = 0;
        for (Document document : temp){
            BuyDoc buyDoc = reAssignment(document);
            buyDocs[i++] = buyDoc;
        }
        return buyDocs;
    }

    /**
     * @Description: 将user转为doc
     * @param user
     * @return: org.bson.Document
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    private Document trans(User user){
        Document document = new Document();
        document.append(uid, user.getUid());
        document.append(password, user.getPassword());
        document.append(name, user.getName());
        document.append(avatar, user.getAvatar());
        document.append(tel, user.getTel());
        document.append(address, user.getAddress());
        List<Document> ggbuyCars = new ArrayList<>();
        for (BuyDoc bd: user.getBuyCars()){
            Document document1 = assignment(bd);
            ggbuyCars.add(document1);
        }
        document.append(gbuyCars, ggbuyCars);

        ggbuyCars = new ArrayList<>();
        BuyDoc[] historyList = user.getHistoryList();
        if (historyList == null){
            historyList = new BuyDoc[0];
        }
        for (BuyDoc bd : historyList){
            Document document1 = assignment(bd);
            ggbuyCars.add(document1);
        }
        document.append(ghistoryList, ggbuyCars);
        return document;
    }

    /**
     * @Description: 将buyDoc转为doc
     * @param bd
     * @return: org.bson.Document
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    private Document assignment(BuyDoc bd){
        Document document1 = new Document();
        document1.append(uid, bd.getUid());
        document1.append(gid, bd.getGid());
        document1.append(time, bd.getTime());
        document1.append(number, bd.getNumber());
        return document1;
    }

    /**
     * @Description: 将doc 转为buyDoc
     * @param document
     * @return: com.liujx.onlineshop.entity.BuyDoc
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    private BuyDoc reAssignment(Document document){
        BuyDoc buyDoc = new BuyDoc();
        if (document.containsKey(uid)){
            buyDoc.setUid(document.getString(uid));
        }
        if (document.containsKey(gid)){
            buyDoc.setGid(document.getString(gid));
        }
        if (document.containsKey(time)){
            buyDoc.setTime(document.getDate(time));
        }
        if (document.containsKey(number)){
            buyDoc.setNumber(document.getInteger(number));
        }
        return buyDoc;

    }
}
