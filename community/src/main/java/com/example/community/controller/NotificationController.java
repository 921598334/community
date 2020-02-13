package com.example.community.controller;

import com.example.community.advice.CustomizeException;
import com.example.community.dto.NotificationDTO;
import com.example.community.enums.NotificationEnum;
import com.example.community.mapper.CommentMapper;
import com.example.community.model.Comment;
import com.example.community.model.Notification;
import com.example.community.model.User;
import com.example.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;




@Controller
public class NotificationController {


    @Autowired
    NotificationService notificationService;

    @Autowired
    CommentMapper commentMapper;


    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name="id") Integer id,
                          Model model,
                          HttpServletRequest request
    ) {


        //在进入该页面时，拦截器会首先进行判断，如果有用户了，用户信息会被放在session中
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) return "redirect:/index";


        Notification notification = notificationService.getById(id);

        //因为点击了所以需要吧状态设置为1
        notification.setStatus(1);
        notificationService.update(notification);

        //更新session
        Integer count = notificationService.unReadCount(user.getId());
        request.getSession().setAttribute("unreadCount",count);


        if(notification.getType()== NotificationEnum.REPLY_QUESTION.getStatus()){
            //如果是对问题的回答，就返回问题
            return "redirect:/question/"+notification.getOuter_id();

        } else{
            //如果是对回答的评论，那么需要返回该评论对应的问题
            //首先需要得到评论的回复
            Comment dbComment = commentMapper.findCommentByParentId(notification.getOuter_id());

            if(dbComment==null){
                throw new CustomizeException("该回复不存在，无法添加评论");
            }
            //得到回复的父id，也就是问题的id,然后得到问题
            return "redirect:/question/"+dbComment.getParent_id();
        }
    }


}
