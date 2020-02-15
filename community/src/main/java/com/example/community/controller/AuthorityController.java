package com.example.community.controller;


import com.example.community.dto.AccessDTO;
import com.example.community.dto.GitHubUser;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.provider.GitHubProvider;
import com.example.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


//github授权登陆,与退出登陆
@Controller
public class AuthorityController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    UserService userService;


    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirectUrl;


    //HttpServletRequest是浏览器给服务器发到请求，所以可以得到session和cookie,
    // HttpServletResponse是服务器返回给浏览器到，所以可以添加cookice到浏览器
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code
            , @RequestParam(name="state") String state
            , HttpServletRequest request
            ,HttpServletResponse response
            , Model model) {


//        Client ID
//        71723b2931d149bfcd48
//        Client Secret
//        0681098767c10521c9be7d8db883451817328090
//
//        http://localhost:8080/callback


        AccessDTO accessDTO = new AccessDTO(clientId,clientSecret,code,redirectUrl,state);
        GitHubUser gitHubUser = gitHubProvider.getUser(gitHubProvider.getAccessToken(accessDTO)) ;

        //得到github用户登陆成功
        if(gitHubUser!=null){


            //随机生成一个token
            String token = UUID.randomUUID().toString();

            //创建时间
            Long creatTime = System.currentTimeMillis();

            User newUser = new User(gitHubUser.getName(),gitHubUser.getId()+"",token,creatTime,creatTime,gitHubUser.getAvatarUrl());


            //在数据库中检查这个github用户是否存在，不存在就创建，否则就更新
            newUser = userService.createOrUpdate(newUser);


            //把用户信息存储到cookie中
            response.addCookie(new Cookie("token",token));


            //把user放入session中，以便能在index中显示,因为拦截器不会拦截index页面，所以需要在这里添加session
            request.getSession().setAttribute("user",newUser);


            //重定向，如果不加redirect，那么浏览器的url不会改变
            return  "redirect:/index";

        //登陆失败
        }else {
            System.out.println("用户不存在");
            return  "redirect:/index";
        }

    }






}
