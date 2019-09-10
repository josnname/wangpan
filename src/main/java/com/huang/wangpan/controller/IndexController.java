package com.huang.wangpan.controller;

import com.huang.wangpan.dto.UserData;
import com.huang.wangpan.mapper.UserMapper;
import com.huang.wangpan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserData userData;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        if (cookies != null) {
            System.out.println("我来了");
            for (Cookie cookie : cookies) {
                token = cookie.getName();
                if ("token".equals(token)) {
                    token = cookie.getValue();
                    System.out.println("token进来");
                    User user=userMapper.searchByToken(token);
                    System.out.println(token);
                    String name = user.getName();
                    System.out.println(name);
                    String id = user.getAccountid();
//                    request.getSession().setAttribute("user",user);
                    userData.setId(id);
                    userData.setName(name);
                    model.addAttribute("name",name);
//                    model.addAttribute("img","/static/timg.jpg");
                    return "interface";
//                    return "redirect:/interface";
                }
            }
        }
        return "login";
    }
}
