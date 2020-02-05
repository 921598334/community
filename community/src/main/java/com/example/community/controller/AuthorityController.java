package com.example.community.controller;


import com.example.community.dto.AccessDTO;
import com.example.community.dto.GitHubUser;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
public class AuthorityController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirectUrl;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name="state") String state, HttpServletRequest request, Model model) {


//        Client ID
//        71723b2931d149bfcd48
//        Client Secret
//        0681098767c10521c9be7d8db883451817328090
//
//        http://localhost:8080/callback



        AccessDTO accessDTO = new AccessDTO(clientId,clientSecret,code,redirectUrl,state);
        GitHubUser gitHubUser = gitHubProvider.getUser(gitHubProvider.getAccessToken(accessDTO)) ;

        System.out.println(gitHubUser.getName());


        //登陆成功
        if(gitHubUser!=null){

            //把用户信息存储到seesion中
            request.getSession().setAttribute("user",gitHubUser);

            //把github用户存储到数据库中
            Long creatTime = System.currentTimeMillis();
            userMapper.InsertUser(new User(gitHubUser.getName(),gitHubUser.getId()+"",UUID.randomUUID().toString(),creatTime,creatTime));


            //重定向，如果不加redirect，那么浏览器的url不会改变
            System.out.println("用户存在");
            return  "redirect:/index";

        //登陆失败
        }else {
            System.out.println("用户不存在");
            return  "redirect:/index";
        }

    }
}
