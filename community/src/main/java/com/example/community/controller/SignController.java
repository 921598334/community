package com.example.community.controller;


import com.example.community.model.User;
import com.example.community.provider.FileUpload;
import com.example.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
public class SignController {

    @Autowired
    UserService userService;

    @Autowired
    FileUpload fileUpload;


    @GetMapping("/sign")
    public String sign(){
        return "sign";
    }


    @PostMapping("/sign")
    public String sign(HttpServletRequest request,
                       Model model,
                       @RequestParam(name="username",defaultValue="") String userName,
                       @RequestParam(name="userpwd",defaultValue="") String password,
                       @RequestParam(name="re_userpwd",defaultValue="") String re_userpwd,
                       @RequestParam(name="avater",required = false) String avatar,
                       @RequestParam(name="file",defaultValue = "/images/pika.jpg") MultipartFile multipartFile){


        if(userName.equals("")){
            System.out.println("输入用户名为空");
            model.addAttribute("error","用户名不能为空");
            return "sign";

        }

        if(password.equals("")){
            System.out.println("输入密码为空");
            model.addAttribute("error","密码不能为空");
            return "sign";
        }

        if(re_userpwd.equals("")){
            System.out.println("确认密码为空");
            model.addAttribute("error","密码不能为空");
            return "sign";
        }

        if(!re_userpwd.equals(password)){
            System.out.println("2次输入的密码不一致");
            model.addAttribute("error","2次输入的密码不一致");
            return "sign";
        }


        if(userService.findByUserName(userName)!=null){
            System.out.println("用户名已经存在，请重新输入");
            model.addAttribute("error","用户名已经存在，请重新输入");
            return "sign";
        }



        //开始上传头像
        String path = fileUpload.uploadImage(multipartFile);



        User user = new User();
        user.setName(userName);
        user.setPasswd(password);
        Long createTime = System.currentTimeMillis();
        user.setGmt_create(createTime);
        user.setGmt_modified(createTime);

        if(path==null)
            user.setAvatar_url("/images/pika.jpg");
        else
            user.setAvatar_url(path);




        userService.createOrUpdate(user);

        System.out.println("创建用户成功");
        model.addAttribute("info","创建用户成功");


        return  "login";



    }
}
