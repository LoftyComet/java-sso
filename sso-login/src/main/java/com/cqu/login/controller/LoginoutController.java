package com.cqu.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cqu.login.pojo.User;
import com.cqu.login.utils.LoginCacheUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/loginout")
public class LoginoutController {
    @GetMapping
    public String doLoginout(@RequestParam(required = false,defaultValue = "") 
    String target,User user, HttpSession session, HttpServletResponse response, 
    @CookieValue(required = false, value = "TOKEN")Cookie cookie){

        if (StringUtils.isEmpty(target)) {
            target = "http://localhost:9010/view/index";
        }
        
        if (cookie != null) {
            cookie.setMaxAge(0); // 清除cookie
            LoginCacheUtil.loginUser.remove(cookie.getValue()); // 清除登陆记录
            cookie.setValue(null);
            Cookie newCookie=new Cookie("TOKEN",null); //删除名称为TOKEN的Cookie
            newCookie.setMaxAge(0); //立即删除型
            newCookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
            response.addCookie(newCookie); //重新写入，将覆盖之前的
        }
        
        // 重定向到target地址
        return "redirect:" + target;
        //return "login";
    }
}
