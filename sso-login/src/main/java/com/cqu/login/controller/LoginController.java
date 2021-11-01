package com.cqu.login.controller;

import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cqu.login.pojo.User;
import com.cqu.login.utils.LoginCacheUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Set<User> dbUsers;
    static {
        dbUsers = new HashSet<>();
        dbUsers.add(new User(0,"DJJ","123456"));
        dbUsers.add(new User(1,"DJJ2","810975"));
        dbUsers.add(new User(2,"DJJ3","810975"));
    } // 模拟用户数据库

    @PostMapping
    public String doLogin(User user, HttpSession session, HttpServletResponse response){
        String target = (String) session.getAttribute("target");
        Optional<User> first = dbUsers.stream().filter(dbUser ->dbUser.getUsername()
            .equals(user.getUsername())&&dbUser.getPassword().equals(user.getPassword()))
            .findFirst(); // 模拟数据库查询用
        if (first.isPresent()){
            // 保存用户登陆信息
            String token = UUID.randomUUID().toString();
            // 创建需要的cookies并返回
            Cookie cookie = new Cookie("TOKEN", token);
            cookie.setDomain("localhost"); //设定域名
            response.addCookie(cookie);
            LoginCacheUtil.loginUser.put(token, first.get());
        }
        else{
            // 登陆失败
            session.setAttribute("msg", "用户名或密码错误");
            return "login";
        }
        
        // 重定向到target地址
        return "redirect:" + target;
    }

    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<User> getUserInfo(String token) {
        if (!StringUtils.isEmpty(token)){
            User user = LoginCacheUtil.loginUser.get(token);
            return ResponseEntity.ok(user);
            
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
