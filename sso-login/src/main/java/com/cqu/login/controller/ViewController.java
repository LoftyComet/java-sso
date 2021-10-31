package com.cqu.login.controller;

import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
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
                                                ,HttpSession session) {
        if (StringUtils.isEmpty(target)) {
            target = "http://localhost:9010";
        }
        // 要做target地址是否合法的检验
        // 重定向地址
        session.setAttribute("target", target); //将地址存起来
        return "login";
    }
}
