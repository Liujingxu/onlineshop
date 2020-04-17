package com.liujx.onlineshop.controller;

import com.liujx.onlineshop.entity.BuyDoc;
import com.liujx.onlineshop.entity.Good;
import com.liujx.onlineshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: onlineshop
 * @description: this is a controller for function buy
 * @author: Liujx
 * @create: 2020-04-13 21:34
 **/
@RequestMapping("/buy")
@Controller
public class BuyCarController {

    @Autowired
    private ShoppingCar shoppingCar;

    @Autowired
    private GoodUpdate goodUpdate;

    @Autowired
    private BackLogic backLogic;

    @Autowired
    private GoodsQuery goodsQuery;


    /**
     * @Description: 购买功能
     * @param session
     * @param gid
     * @param number
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    @RequestMapping("buy")
    public ModelAndView buy(HttpSession session, @RequestParam("gid") String gid, @RequestParam("number") Integer number){
        ModelAndView mv = new ModelAndView("cars");
        BuyDoc buyDoc = new BuyDoc();
        buyDoc.setGid(gid);
        buyDoc.setTime(new Date());
        buyDoc.setNumber(number);
        List<BuyDoc> docs = shoppingCar.addCar(session, buyDoc);
        List<Good> goods = getGoodsByDoc(docs);

        goodUpdate.updateGoods(gid, -number);
        mv.addObject("docs", docs);
        mv.addObject("goods", goods);
        Float result = backLogic.calAllList((String) session.getAttribute(AbstractLoginService.TOKEN));
        mv.addObject("cal", result);
        return mv;
    }

    /**
     * @Description: 查看购物车
     * @param session
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    @RequestMapping("check")
    public ModelAndView check(HttpSession session){
        ModelAndView mv = new ModelAndView("cars");
        List<BuyDoc> docs = shoppingCar.checkCar(session);
        mv.addObject("docs", docs);
        Float result = backLogic.calAllList((String) session.getAttribute(AbstractLoginService.TOKEN));
        mv.addObject("cal", result);
        List<Good> goods = getGoodsByDoc(docs);
        mv.addObject("goods", goods);

        return mv;
    }

    /**
     * @Description: 删除购物车的一条记录
     * @param session
     * @param gid
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    @RequestMapping("delete/{gid}")
    public ModelAndView delete(HttpSession session, @PathVariable("gid")String gid){
        ModelAndView mv = new ModelAndView("cars");
        List<BuyDoc> docs = shoppingCar.deleteCar(session, gid);
        mv.addObject("docs", docs);
        List<Good> goods = getGoodsByDoc(docs);
        mv.addObject("goods", goods);
        Float result = backLogic.calAllList((String) session.getAttribute(AbstractLoginService.TOKEN));
        mv.addObject("cal", result);
        return mv;
    }

    /**
     * @Description: 结算功能
     * @param session
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/18
     */
    @RequestMapping("sub")
    public ModelAndView submit(HttpSession session){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("cars");
        Float result = backLogic.calAllList((String) session.getAttribute(AbstractLoginService.TOKEN));
        Integer docs = shoppingCar.submit(session);
        mv.addObject("sub", docs);
        mv.addObject("cal", result);
        return mv;
    }

    private List<Good> getGoodsByDoc(List<BuyDoc> docs){
        List<Good> goods = new ArrayList<>();
        for (BuyDoc bd : docs){
            Good good = goodsQuery.getGoodsById(bd.getGid());
            goods.add(good);
        }
        return goods;
    }



}
