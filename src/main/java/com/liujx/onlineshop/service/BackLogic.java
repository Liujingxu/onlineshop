package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.BuyDoc;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 */
public interface BackLogic {

    Float calculateItem(BuyDoc buyDoc);

    Float calAllList(String uid);

    Map<String, Object> calUserHistory(String uid);
}
