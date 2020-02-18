package com.example.community.controller;


//用来完成github用户与本地用户的绑定

import com.example.community.model.GithubUser;
import com.example.community.model.User;
import com.example.community.service.GitHubUserService;
import com.example.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BindController {

    @Autowired
    UserService userService;

    @Autowired
    GitHubUserService gitHubUserService;

    @GetMapping("/bind")
    public String bind(){
        return "bind";
    }


    @PostMapping("/bind")
    public String bind(@RequestParam(name="username",defaultValue="") String userName,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       Model model){


        if(userName.equals("")){
            System.out.println("输入用户名为空");
            model.addAttribute("error","用户名不能为空,如果没有注册过该网站的用户，请点击注册按钮进行注册后再进行绑定");
            return "bind";

        }


        User user = userService.findByUserName(userName);

        if(user==null){
            System.out.println("用户名不存在，请重新输入");
            model.addAttribute("error","用户名不存在，请重新输入，,如果没有注册过该网站的用户，请点击注册按钮进行注册后再进行绑定");
            return "bind";
        }

        if(user.getGithub_id()!=null){
            System.out.println("该用户名已和其他账号绑定，请确认输入的用户名是否正确");
            model.addAttribute("error","该用户名已和其他账号绑定，请确认输入的用户名是否正确");
            return "bind";
        }


        Long modifiedTime = System.currentTimeMillis();
        user.setGmt_modified(modifiedTime);


        GithubUser githubUser = (GithubUser) request.getSession().getAttribute("gitHubUserFromDB");

        githubUser.setUser_id(user.getId());
        user.setGithub_id(githubUser.getId());

        //更新外key
        User newUser = userService.createOrUpdate(user);
        gitHubUserService.createOrUpdate(githubUser);


        //完成后直接登陆
        request.getSession().setAttribute("user",newUser);


        return  "redirect:/index";
    }

}
