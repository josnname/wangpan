package com.huang.wangpan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ExitController {
    @RequestMapping("/exit")
    public  String exit(HttpServletRequest request){
        System.out.println("退出来了");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String token = cookie.getName();
            if ("token".equals(token)) {
                cookie.setMaxAge(0);
                break;
            }
        }
        return "login";
    }
}
