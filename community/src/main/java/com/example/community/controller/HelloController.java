package com.example.community.controller;


import com.example.community.dto.PageDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    //每次进入index页面是，首先判断浏览器上到cookie到token和能不能在数据库中找到对应到用户，如果有就直接登陆
    //如果在数据中没有，或者cookie过期就显示登陆按钮
    @GetMapping("/index")
    public String greeting(HttpServletRequest request,
                           Model model,
                           @RequestParam(name="page",defaultValue="1") Integer page,
                           @RequestParam(name="size",defaultValue="5") Integer size
    ) {




        //从数据库中得到分页的数据
        PageDTO pageDTO = questionService.getList(page,size);

        model.addAttribute("pageDTO",pageDTO);

        //这个表示寻找index的页面进行显示,不用redirect表示返回页面（浏览器的url不会修改）,同时不会走controller,也就意味着页面中的变量可能没有值
        return "index";
    }


}
