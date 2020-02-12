package com.example.community.controller;

import com.example.community.cache.TagCache;
import com.example.community.dto.TagDTO;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


//发布文章相关
@Controller
public class PublishController {


    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserMapper userMapper;


    //是get请求时渲染页面
    @GetMapping("/publish")
    public String greeting(Model model){

        //标签
        model.addAttribute("tags",TagCache.get());

        return "publish";
    }


    //用户点击修改时,需要把数据都显示在界面上
    @GetMapping("/publish/{id}")
    public String update(@PathVariable(name = "id") Integer id,
                         Model model,
                         HttpServletRequest request
    ){

        //标签
        model.addAttribute("tags",TagCache.get());

        //根据id从数据库中得到文章
        Question question = questionMapper.getById(id);

        //这个是一个隐藏属性
        model.addAttribute("id",question.getId());
        //把用户输入的信息先缓存起来,如果用户有什么没有输入，那重新回到这个页面的时候可以保存
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());

        //在进入该页面时，拦截器会首先进行判断，如果有用户了，用户信息会被放在session中
        User user = (User)request.getSession().getAttribute("user");

        //如果用户没有登陆，就返回
        if(user == null){
            model.addAttribute("error","用户名没有登陆");
            return "publish";
        }


        return "publish";
    }




    //用户发帖时的post请求时，添加或者更新问题到数据库
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name="title") String title,
            @RequestParam(name="description") String description,
            @RequestParam(name="tag") String tag,
            @RequestParam(name="id") Integer id,

            HttpServletRequest request,
            Model model
    ){

        //把用户输入的信息先缓存起来,如果用户有什么没有输入，那重新回到这个页面的时候可以保存
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        //id是隐藏属性，自由更新页面都时候才有
        model.addAttribute("id",id);

        //得到所有正确的标签
        List<TagDTO> standTagDTOs =  TagCache.get();
        model.addAttribute("tags",standTagDTOs);
        List<String> standTags = new ArrayList<>();
        for(TagDTO tagDTO:standTagDTOs){

            for(String s:tagDTO.getTags()){
                standTags.add(s);
            }
        }




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

        //判断用户输入的标签集合有没有错误（用户数据的标签集合是否被包含）
        String[] tags = tag.split(",");
        //如果存在相同元素standTags只会保留相同的，然后判断standTags和tags是否相同
        standTags.retainAll(Arrays.asList(tags));
        if(!standTags.containsAll(Arrays.asList(tags))){
            model.addAttribute("error","输入的标签有错误");
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

        Question question = new Question(title,description,tag,time,time,user.getId(),0,0,0);
        //表明该用户存在
        if(id!=null && id!=0){
            question.setId(id);
        }


        questionService.createOrUpdate(question);

        return  "redirect:/index";
    }

}
