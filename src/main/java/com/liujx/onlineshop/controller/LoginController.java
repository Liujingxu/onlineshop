package com.liujx.onlineshop.controller;

import com.liujx.onlineshop.entity.User;
import com.liujx.onlineshop.service.AbstractLoginService;
import com.liujx.onlineshop.service.LoginService;
import com.liujx.onlineshop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @program: onlineshop
 * @description: this is the controller for login and regist
 * @author: Liujx
 * @create: 2020-04-13 16:54
 **/
@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @Description: 登录界面
     * @return: java.lang.String
     * @Author: Liujx
     * @Date: 2020/4/13
     */
    @RequestMapping("vlogin")
    public String vlogin(){

        return "vlogin";
    }

    @RequestMapping("vcreate")
    public String vcreate(){
        return "vcreate";
    }

    /**
     * @Description: 登录验证
     * @param username
     * @param password
     * @param session
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/13
     */
    @RequestMapping("in")
    public ModelAndView login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session){

        ModelAndView mv = new ModelAndView();
        User user = new User(username, password, null);
        User user1 = loginService.login(user);
        if (user1 != null){
            if (!"]游客".equals(user1.getName())){
                session.setAttribute(AbstractLoginService.TOKEN, user1.getUid());
                mv.setViewName("redirect:/user/info");

            }else {
                mv.setViewName("vlogin");
                mv.addObject("err","用户名或密码错误");
            }
        }else {
            mv.setViewName("vlogin");
            mv.addObject("err", "用户不存在");
        }
        return mv;
    }

    /**
     * @Description: 注册新用户
     * @param uid
     * @param password
     * @param name
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: Liujx
     * @Date: 2020/4/13
     */
    @RequestMapping("create")
    public ModelAndView register(@RequestParam("uid") String uid, @RequestParam("pass") String password, @RequestParam("name") String name){
        ModelAndView mv = new ModelAndView();
        User user1 = new User(uid, password, name);
        if (userInfoService.addUser(user1)){
            mv.setViewName("vlogin");
        }else {
            mv.addObject("err", "注册不成功");
            mv.setViewName("vcreate");
        }

        return mv;
    }

    /**
     * @Description: 登出
     * @param httpSession
     * @return: java.lang.String
     * @Author: Liujx
     * @Date: 2020/4/13
     */
    @RequestMapping("logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute(AbstractLoginService.TOKEN);
        return "redirect:/query/iindex";
    }


}
