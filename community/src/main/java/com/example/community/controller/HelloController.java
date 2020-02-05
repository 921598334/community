package com.example.community.controller;


import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HelloController {


    @Autowired
    private UserMapper userMapper;

    //每次进入index页面是，首先判断浏览器上到cookie到token和能不能在数据库中找到对应到用户，如果有就直接登陆
    //如果在数据中没有，或者cookie过期就显示登陆按钮
    @GetMapping("/index")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name
            , HttpServletRequest request) {

        //通过cookie得到在数据库中得到用户
        Cookie[] cookies = request.getCookies();

        if(cookies==null || cookies.length==0)
        {
            System.out.println("token不存在");
            return "index";
        }

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                //在数据库中看看有没有token
                Map<String,Object> userMap = userMapper.findByToken(cookie.getValue()) ;

                User user = new User();

                for (String key : userMap.keySet()) {
                    //数据库                                 对象
                    if(key.equals("id")) user.setId(Integer.parseInt(userMap.get(key).toString()));
                    if(key.equals("account_id")) user.setAccountId((userMap.get(key).toString()));
                    if(key.equals("name")) user.setName((userMap.get(key).toString()));
                    if(key.equals("token")) user.setToken((userMap.get(key).toString()));
                    if(key.equals("gmt_create")) user.setGmtCreate(Long.parseLong(userMap.get(key).toString()));
                    if(key.equals("gmt_modified")) user.setGmtModitied(Long.parseLong(userMap.get(key).toString()));
                }



                //把用户信息存储到seesion中，然后在前段判断这个session中有没有这个用户，如果没有就显示登陆
                request.getSession().setAttribute("user",user);
                System.out.println("在数据库中找到token");
                break;
            }

        }



        //这个表示寻找index的页面进行显示
        return "index";
    }


}
