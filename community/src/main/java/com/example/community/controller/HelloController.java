package com.example.community.controller;


import com.example.community.advice.CustomizeException;
import com.example.community.cache.HotTagCache;
import com.example.community.cache.TagCache;
import com.example.community.dto.PageDTO;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.service.NotificationService;

import com.example.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

//主界面
@Controller
@Slf4j
public class HelloController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    NotificationService notificationService;





    //每次进入index页面是，首先判断浏览器上到cookie到token和能不能在数据库中找到对应到用户，如果有就直接登陆
    //如果在数据中没有，或者cookie过期就显示登陆按钮
    @GetMapping("/index")
    public String greeting(HttpServletRequest request,
                           Model model,
                           @RequestParam(name="page",defaultValue="1") Integer page,
                           @RequestParam(name="size",defaultValue="5") Integer size,
                           @RequestParam(name="search",defaultValue = "") String search,
                           @RequestParam(name="tag",defaultValue = "") String tag,
                           @RequestParam(name="isChecked",defaultValue = "false") boolean isChecked
    ) {






        //用户是否登陆判断
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            Integer count = notificationService.unReadCount(user.getId());
            request.getSession().setAttribute("unreadCount",count);
        }


        //当点击左上角的回到主页时，相当于清楚所有的条件搜索
        if(tag.equals("") && search.equals("")){
            model.addAttribute("tag","");
            model.addAttribute("search","");
        }



        //当标签或者查询条件更新时
        if(!tag.equals("")){
            if(isChecked==false){
                model.addAttribute("tag",tag);
            }else {
                model.addAttribute("tag","");
                tag = "";
            }

        }


        if(!search.equals("")){
            model.addAttribute("search",search);
        }



        PageDTO pageDTO = null;

        pageDTO = questionService.getListByTagAndSearch(page,size,tag,search);



        model.addAttribute("pageDTO",pageDTO);


        //热门标签
        model.addAttribute("tagResult",HotTagCache.tagResult);




        //这个表示寻找index的页面进行显示,不用redirect表示返回页面（浏览器的url不会修改）,同时不会走controller,也就意味着页面中的变量可能没有值
        return "index";
    }






}
