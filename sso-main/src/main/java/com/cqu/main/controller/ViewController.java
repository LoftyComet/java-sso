package com.cqu.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 页面调整逻辑
@Controller
@RequestMapping("/view")
public class ViewController {
    // 跳转到登陆界面
    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }
}
