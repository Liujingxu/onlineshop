package com.liujx.onlineshop.controller;

import com.liujx.onlineshop.entity.BuyDoc;
import com.liujx.onlineshop.entity.Good;
import com.liujx.onlineshop.entity.User;
import com.liujx.onlineshop.service.AbstractLoginService;
import com.liujx.onlineshop.service.BackLogic;
import com.liujx.onlineshop.service.GoodsQuery;
import com.liujx.onlineshop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: onlineshop
 * @description: this is a controller to update user's option
 * @author: Liujx
 * @create: 2020-04-13 18:31
 **/
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private GoodsQuery goodsQuery;

    @Autowired
    private BackLogic backLogic;


    @RequestMapping("vupdate")
    public ModelAndView vupdate(HttpSession session){
        ModelAndView mv = new ModelAndView("uupdate");
        String uid = (String) session.getAttribute(AbstractLoginService.TOKEN);
        User user = userInfoService.getUserById(uid);
        mv.addObject("user", user);
        return mv;
    }

    /**
     * @Description: 通过id 查找用户详细信息
     * @param session
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/13
     */
    @RequestMapping("info")
    public ModelAndView getUserById(HttpSession session){
        ModelAndView mv = new ModelAndView("uinfo");
        String uid = (String) session.getAttribute(AbstractLoginService.TOKEN);
        User user = userInfoService.getUserById(uid);
        mv.addObject("user", user);
        BuyDoc[] historyList = user.getHistoryList();
        if (historyList == null){
            historyList = new BuyDoc[0];
        }
        List<Good> goods = new ArrayList<>();
        for (BuyDoc bd : historyList){
            goods.add(goodsQuery.getGoodsById(bd.getGid()));
        }
        mv.addObject("historyGoods", goods);
        Map<String, Object> map = backLogic.calUserHistory(uid);
        mv.addObject("map", map);

        return mv;
    }

    /**
     * @Description: 更新用户
     * @param user
     * @param session
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/14
     */
    @RequestMapping("update")
    public ModelAndView updateUser(HttpSession session, User user){

        ModelAndView mv = new ModelAndView("uinfo");
        String uid = (String) session.getAttribute(AbstractLoginService.TOKEN);
        User user1 = userInfoService.getUserById(uid);
        user.setUid(uid);
        user.setBuyCars(user1.getBuyCars());
        user.setHistoryList(user1.getHistoryList());
        User updateUser = userInfoService.updateUser(user);
        mv.addObject("user", updateUser);
        return mv;
    }

    @RequestMapping("delete")
    @ResponseBody
    public String deleteUser(){
//        ModelAndView mv = new ModelAndView("iindex");
        return "test success";
    }



}
