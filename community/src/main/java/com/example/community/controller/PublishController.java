package com.example.community.controller;

import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Map;


//发布文章相关
@Controller
public class PublishController {


    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;


    //是get请求时渲染页面
    @GetMapping("/publish")
    public String greeting(){

        return "publish";
    }


    //用户发帖时的post请求时
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name="title") String title,
            @RequestParam(name="description") String description,
            @RequestParam(name="tag") String tag,
            HttpServletRequest request,
            Model model
    ){

        //把用户输入的信息先缓存起来,如果用户有什么没有输入，那重新回到这个页面的时候可以保存
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        //验证用户输入的是不是为空
        if(title==null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if(description==null || description == ""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }

        if(tag==null || tag == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        //在进入该页面时，拦截器会首先进行判断，如果有用户了，用户信息会被放在session中
        User user = (User)request.getSession().getAttribute("user");

        //如果用户没有登陆，就返回
        if(user == null){
            model.addAttribute("error","用户名没有登陆");
            return "publish";
        }

        //存在用户时就把用户的提问添加到数据库中,然后返回首页
        Long time = System.currentTimeMillis();

        questionMapper.create(new Question(title,description,tag,time,time,user.getId(),0,0,0));
        return  "redirect:/index";
    }

}
