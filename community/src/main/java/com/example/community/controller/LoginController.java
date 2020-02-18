package com.example.community.controller;


import com.example.community.model.User;
import com.example.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(){

         return  "login";
    }


    @PostMapping("/login")
    public String login( @RequestParam(name="username",defaultValue="") String userName,
                         @RequestParam(name="userpwd",defaultValue="") String password,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         Model model){

        if(userName.equals("")){
            System.out.println("输入用户名为空");
            model.addAttribute("error","用户名不能为空");
            return "login";

        }

        if(password.equals("")){
            System.out.println("输入密码为空");
            model.addAttribute("error","密码不能为空");
            return "login";
        }


        User user = new User();
        user.setName(userName);
        user.setPasswd(password);
        user = userService.checkUser(user);

        if(user!=null){

            //随机生成一个token
            String token = UUID.randomUUID().toString();

            //创建时间
            Long time = System.currentTimeMillis();

            user.setToken(token);
            user.setGmt_modified(time);
            //在数据库中检查这个用户是否存在，不存在就创建，否则就更新(主要更新token)
            user = userService.createOrUpdate(user);


            //把用户信息存储到cookie中
            response.addCookie(new Cookie("token",token));


            //把user放入session中，以便能在index中显示,因为拦截器不会拦截login页面，所以需要在这里添加session
            request.getSession().setAttribute("user",user);


            return  "redirect:/index";
        }else {
            System.out.println("输入用户名或者密码有错误");
            model.addAttribute("error","输入用户名或者密码有错误");
            return "login";
        }


    }




    //退出登陆时需要移除cookie与session
    @GetMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response
    ){

        request.getSession().removeAttribute("user");

        //java删除cookie的方法
        Cookie cookie = new Cookie("token",null);
        response.addCookie(cookie);
        cookie.setMaxAge(0);
        //删除session
        request.getSession().removeAttribute("unreadCount");
        request.getSession().removeAttribute("user");


        System.out.println("已经退出登陆");
        return  "redirect:/index";
    }
}







