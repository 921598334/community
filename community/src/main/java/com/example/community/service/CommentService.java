package com.example.community.service;


import com.example.community.advice.CustomizeException;
import com.example.community.dto.CommentDTO;
import com.example.community.enums.CommentTypeEnum;
import com.example.community.enums.NotificationEnum;
import com.example.community.enums.NotificationStatusEume;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.NotificationMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Comment;
import com.example.community.model.Notification;
import com.example.community.model.Question;
import com.example.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserMapper userMapper;


    @Autowired
    NotificationService notificationService;

    //这个函数作为一个事务，只有内部的所有操作都成功时才算成功
    @Transactional
    public void insertComment(Comment comment) {

        //当向1个问题添加回复时，如果刚好这个问题被删除了，那么就不存在了
        if(comment.getParent_id() == null ||comment.getParent_id() == 0 ){
            throw new CustomizeException("该问题或者回复不存在(getParent_id为空)，无法添加");
        }

        if(comment.getType() == null ||comment.getParent_id() == 0 ){
            throw new CustomizeException("未知错误");
        }


        //当前是评论时
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){

            //判断回复是否存在，如果存在就评论该回复
           Comment dbComment = commentMapper.findCommentByParentId(comment.getParent_id());

            if(dbComment==null){
                throw new CustomizeException("该回复不存在，无法添加评论");
            }

            Long creatTime = System.currentTimeMillis();
            //添加通知
            Notification notification = new Notification();
            notification.setNotifier(comment.getCommentator());
            notification.setReceiver(dbComment.getCommentator());
            notification.setOuter_id(comment.getParent_id());
            notification.setType(NotificationEnum.REPLY_COMMENT.getStatus());
            notification.setStatus(NotificationStatusEume.UNREAD.getStatus());
            notification.setGmt_create(creatTime);
            notificationService.insert(notification);


        }else
        {
            //回复问题,要首先判断该问题是否存在,需要根据评论的父id去得到问题
            Question question = commentMapper.findQuestionByParentId(comment.getParent_id());

            if(question==null){
                throw new CustomizeException("该问题不存在，无法添加回复");
            }

            //添加问题的评论数
            questionService.addComment(question);

            Long creatTime = System.currentTimeMillis();
            //添加通知
            Notification notification = new Notification();

            notification.setType(NotificationEnum.REPLY_QUESTION.getStatus());
            notification.setGmt_create(creatTime);
            notification.setNotifier(comment.getCommentator());
            notification.setReceiver(question.getCreator());
            notification.setOuter_id(comment.getParent_id());
            notification.setStatus(NotificationStatusEume.UNREAD.getStatus());
            notificationService.insert(notification);

        }

        
        commentMapper.insertComment(comment);


    }


    //得到回复的所有的评论
    public List<CommentDTO> getComments(Integer id){

        List<Comment> comments = commentMapper.getComments(id);

        //与用户绑定
        //根据comment的创建人id寻找创建人
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(Comment comment:comments){

            User user = userMapper.findById(comment.getCommentator());

            if(user==null){
                throw  new CustomizeException("这个评论的用户不存在");
            }

            CommentDTO commentDTO= new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }

        return commentDTOList;

    }


    //根据id得到评论或者回复
    public Comment getCommentById(Integer id){
        return commentMapper.getCommentById(id);
    }

}
