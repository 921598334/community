package com.example.community.controller;

import com.example.community.dto.CommentDTO;
import com.example.community.dto.ResultDTO;
import com.example.community.model.Comment;
import com.example.community.model.User;
import com.example.community.service.CommentDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


//前端通过post发出评论时进行的处理
@Controller
public class CommentController {


    @Autowired
    private CommentDTOService commentDTOService;

    //这种方式会把返回的对象变为json格式
    //CommentDTO是浏览器上按照json格式传送过来的数据
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request,
                       Model model){

        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            return new ResultDTO(2002,"没有登陆");
        }

        Comment comment = new Comment();
        //回复的父id时问题，评论的父id时回复
        comment.setParent_id(commentDTO.getParent_id());
        comment.setComment(commentDTO.getComment());
        //判断当前是评论还是回复
        comment.setType(commentDTO.getType());
        Long time = System.currentTimeMillis();
        comment.setGmt_create(time);
        comment.setGmt_modified(time);
        comment.setCommentator(user.getId());
        comment.setLike_count(0L);


        //把评论添加到数据库中
        commentDTOService.insertComment(comment);

        //还需要让question的评论数添加1

        return new ResultDTO(200,"评论成功");
    }
}
