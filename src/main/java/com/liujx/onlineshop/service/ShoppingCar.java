package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.BuyDoc;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lenovo
 */
public interface ShoppingCar {

    List<BuyDoc> addCar(HttpSession session, BuyDoc buyDoc);

    List<BuyDoc> checkCar(HttpSession session);

    List<BuyDoc> deleteCar(HttpSession session, String gid);

    Integer submit(HttpSession session);
}
