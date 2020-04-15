package com.liujx.onlineshop.config;

import com.liujx.onlineshop.service.AbstractLoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: onlineshop
 * @description: this is a class to intercept request
 * @author: Liujx
 * @create: 2020-04-14 22:38
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = (String) request.getSession().getAttribute(AbstractLoginService.TOKEN);
        if (token == null){
            response.sendRedirect("/login/vlogin");
        }
        return true;
    }
}
