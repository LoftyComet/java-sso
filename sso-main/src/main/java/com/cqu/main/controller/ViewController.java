package com.cqu.main.controller;


import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

// 页面调整逻辑
@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private RestTemplate restTemplate;
    
    
    // 跳转到登陆界面
    @GetMapping("/index")
    public String toIndex(@CookieValue(required = false, value = "TOKEN")Cookie cookie, HttpSession session) {
        if (cookie != null) {
            String token = cookie.getValue();
            if (!StringUtils.isEmpty(token)) {
                Map result = restTemplate.getForObject("http://localhost:9000/login/info?token=" + token, Map.class);
                session.setAttribute("loginUser", result);
            }
        }
        return "index";
    }
}
