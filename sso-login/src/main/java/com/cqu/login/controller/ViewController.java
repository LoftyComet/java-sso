package com.cqu.login.controller;

import org.thymeleaf.util.StringUtils;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.cqu.login.pojo.User;
import com.cqu.login.utils.LoginCacheUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 页面调整逻辑
@Controller
@RequestMapping("/view")
public class ViewController {
    // 跳转到登陆界面
    @GetMapping("/login")
    public String toLogin(@RequestParam(required = false,defaultValue = "") String target 
                                ,HttpSession session,@CookieValue(required = false,value = "TOKEN") Cookie cookie) {
        if (StringUtils.isEmpty(target)) {
            target = "http://localhost:9010";
        }
        // 如果用户已经登录再次访问登录页面，重定向       
        if (cookie != null) {
            String value = cookie.getValue();
            User user = LoginCacheUtil.loginUser.get(value);
            if (user != null) {
                return "redirect:" + target;
            }
        }
        
        // TODO target地址是否合法的检验
        // 重定向地址
        session.setAttribute("target", target); //将地址存起来
        return "login";
    }
}
