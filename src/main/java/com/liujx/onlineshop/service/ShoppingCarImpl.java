package com.liujx.onlineshop.service;

import com.liujx.onlineshop.dao.GoodDao;
import com.liujx.onlineshop.dao.Logger;
import com.liujx.onlineshop.dao.UserDao;
import com.liujx.onlineshop.entity.BuyDoc;
import com.liujx.onlineshop.entity.Good;
import com.liujx.onlineshop.entity.Log;
import com.liujx.onlineshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lenovo
 */
@Service
public class ShoppingCarImpl implements ShoppingCar {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private Logger logger;

    @Override
    public List<BuyDoc> addCar(HttpSession session, BuyDoc buyDoc) {
        String uid = (String) session.getAttribute(LoginService.TOKEN);
        if (uid == null){
            logger.info("X用户不存在", Log.SYSTEM);
            return null;
        }

        logger.info("调用购买功能...", uid);

        logger.info("访问数据库:获取详细信息", uid);
        User user = userDao.findById(uid);
        buyDoc.setUid(uid);
        List<BuyDoc> carsByList = user.getBuyCarsByList();
        ArrayList<BuyDoc> list = new ArrayList<>(carsByList);
        User user1 = user;
        if (list.add(buyDoc)){
            user.setBuyCars(list.toArray(new BuyDoc[0]));
            logger.info("访问数据库:修改用户信息", uid);
            user1 = userDao.updateUser(user);
        }
        logger.info("返回符合结果数据集", uid);
        return user1.getBuyCarsByList();
    }

    @Override
    public List<BuyDoc> checkCar(HttpSession session) {
        String uid = (String) session.getAttribute(LoginService.TOKEN);
        if (uid == null){
            logger.info("X用户不存在", Log.SYSTEM);
            return null;
        }

        logger.info("调用查看购物车功能...", uid);

        logger.info("访问数据库:根据id查询商品数据", uid);
        User user = userDao.findById(uid);
        return user.getBuyCarsByList();
    }

    @Override
    public List<BuyDoc> deleteCar(HttpSession session, String gid) {
        String uid = (String) session.getAttribute(LoginService.TOKEN);
        if (uid == null || gid == null){
            if (uid == null){
                logger.info("X用户不存在", Log.SYSTEM);
            }
            if (gid == null){
                logger.info("gid不存在", Log.SYSTEM);
            }
            return null;
        }

        logger.info("调用删除购买车功能...", uid);

        logger.info("访问数据库:根据id查询商品数据", uid);
        User user = userDao.findById(uid);
        List<BuyDoc> userCar = user.getBuyCarsByList();
        ArrayList<BuyDoc> list = new ArrayList<>(userCar);
        AtomicReference<BuyDoc> target = new AtomicReference<>();
        userCar.forEach(buyDoc -> {
            if (gid.equals(buyDoc.getGid())){
                target.set(buyDoc);
            }
        });
        BuyDoc buyDoc = target.get();
        list.remove(buyDoc);
        Good good = goodDao.findById(buyDoc.getGid());
        good.setNumber(good.getNumber() + buyDoc.getNumber());
        goodDao.updateGood(good);
        BuyDoc[] buyDocs = list.toArray(new BuyDoc[0]);
        user.setBuyCars(buyDocs);
        logger.info("访问数据库:修改用户数据", uid);
        User user1 = userDao.updateUser(user);

        return user1.getBuyCarsByList();
    }


    @Override
    public Integer submit(HttpSession session) {
        String uid = (String) session.getAttribute(LoginService.TOKEN);
        if (uid == null){
            logger.info("X用户不存在", Log.SYSTEM);
            return null;
        }
        logger.info("调用提交购物车功能...", uid);

        logger.info("访问数据库:根据id查询商品数据", uid);
        User user = userDao.findById(uid);
        List<BuyDoc> cars = user.getBuyCarsByList();
        ArrayList<BuyDoc> ccars = new ArrayList<>(cars);
        List<BuyDoc> historyList = user.getHistoryListByList();
        ArrayList<BuyDoc> hbuyDocs = new ArrayList<>(historyList);
        int result = 0;
        if (hbuyDocs.addAll(cars)){
            logger.info("过滤数据", Log.SYSTEM);
            result = ccars.size();
            ccars.clear();
            user.setBuyCars(ccars.toArray(new BuyDoc[0]));
            user.setHistoryList(hbuyDocs.toArray(new BuyDoc[0]));
            logger.info("访问数据库:修改用户数据", uid);
            User user1 = userDao.updateUser(user);
        }
        return result;
    }

}
