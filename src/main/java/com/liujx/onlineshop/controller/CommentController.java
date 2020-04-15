package com.liujx.onlineshop.controller;

import com.liujx.onlineshop.entity.Evaluate;
import com.liujx.onlineshop.service.AbstractLoginService;
import com.liujx.onlineshop.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @program: onlineshop
 * @description: this class control comment
 * @author: Liujx
 * @create: 2020-04-15 21:43
 **/
@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("vcom")
    public ModelAndView vcomment(HttpSession session, @RequestParam("gid") String gid){
        ModelAndView mv = new ModelAndView();
        String uid = (String) session.getAttribute(AbstractLoginService.TOKEN);
        if (uid == null){
            mv.setViewName("vlogin");
        }
        mv.addObject("gid", gid);
        mv.setViewName("com");
        return mv;
    }

    @RequestMapping("com")
    public String comments(HttpSession session, @RequestParam("level") Integer level, @RequestParam("context") String context, @RequestParam("gid") String gid){
        ModelAndView mv = new ModelAndView();
        String uid = (String) session.getAttribute(AbstractLoginService.TOKEN);
        if (uid == null){
            mv.setViewName("vlogin");
        }
        Evaluate evaluate = new Evaluate();
        evaluate.setEid(String.valueOf(System.currentTimeMillis()));
        evaluate.setLevel(level);
        evaluate.setContext(context);
        evaluate.setUid(uid);

        commentService.addComment(gid, evaluate);

        return "redirect:/user/info";

    }
}
