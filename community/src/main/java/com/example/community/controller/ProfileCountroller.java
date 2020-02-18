package com.example.community.controller;


import com.example.community.dto.PageDTO;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.service.NotificationService;

import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

//我的提问与我的回复相关的
@Controller
public class ProfileCountroller {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name="page",defaultValue="1") Integer page,
                          @RequestParam(name="size",defaultValue="5") Integer size
    ){



        //在进入该页面时，拦截器会首先进行判断，如果有用户了，用户信息会被放在session中
        User user = (User)request.getSession().getAttribute("user");

        if(user == null) return  "redirect:/index";


        if(action.equals("questions")){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");

            //得到用户读问题
            PageDTO paginationDTO = questionService.getList(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);

        }else if(action.equals("replies")){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");

            //得到我读通知数目
            PageDTO paginationDTO = notificationService.getList(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);
        }




        return "profile";
    }

}
