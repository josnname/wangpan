package com.huang.wangpan.controller;

import com.huang.wangpan.dto.AccessTokenDTO;
import com.huang.wangpan.dto.GithubUser;
import com.huang.wangpan.mapper.UserMapper;
import com.huang.wangpan.model.User;
import com.huang.wangpan.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Resource
    UserMapper userMapper;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setClinet_id(clientId);
        accessTokenDTO.setState(state);
        System.out.println("我来了callback");
        String access_token=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser=githubProvider.getUser(access_token);
        if(githubUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountid(githubUser.getId()+"");
            user.setGmtcreate(System.currentTimeMillis()+"");
            user.setGmtmodified(user.getGmtcreate());
            userMapper.insert(user);
            Cookie cookie = new Cookie("token",token);
            cookie.setMaxAge(350000);
            response.addCookie(cookie);
            return "redirect:/";
        }
        return "hello";
    }
}
