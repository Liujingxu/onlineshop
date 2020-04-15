package com.liujx.onlineshop.controller;

import com.liujx.onlineshop.entity.Good;
import com.liujx.onlineshop.service.GoodUpdate;
import com.liujx.onlineshop.service.GoodsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.KeyGenerator;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: onlineshop
 * @description: this is a controller which decide how to get goods
 * @author: Liujx
 * @create: 2020-04-13 17:24
 **/
@RequestMapping("/query")
@Controller
public class QueryController {

    @Autowired
    private GoodsQuery goodsQuery;

    @Autowired
    private GoodUpdate goodUpdate;


    /**
     * @Description: 首页
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/13
     */
    @RequestMapping("iindex")
    public ModelAndView iindex(HttpSession session){
        ModelAndView mv = new ModelAndView("find");
        List<Good> goods = goodsQuery.getAllGoods(0);
        mv.addObject("goods", goods);
        return mv;
    }

    /**
     * @Description: 通过关键字搜索相关商品
     * @param name
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/13
     */
    @RequestMapping("find")
    public ModelAndView find(@RequestParam("key") String name){

        ModelAndView mv = new ModelAndView("find");
        List<Good> goods = goodsQuery.getGoodsByName(name);
        mv.addObject("goods", goods);
        return mv;
    }

    /**
     * @Description: 通过id 获取商品详情页
     * @param gid
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/13
     */
    @RequestMapping("info")
    public ModelAndView goodInfo(@RequestParam("gid") String gid){

        ModelAndView mv = new ModelAndView("goodInfo");
        Good good = goodsQuery.getGoodsById(gid);
        goodUpdate.addClick(gid);
        mv.addObject("good", good);
        return mv;
    }
}
