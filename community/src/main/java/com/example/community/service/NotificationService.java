package com.example.community.service;


import com.example.community.dto.NotificationDTO;
import com.example.community.dto.PageDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.enums.NotificationEnum;
import com.example.community.mapper.NotificationMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Comment;
import com.example.community.model.Notification;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.corba.se.idl.constExpr.Not;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentService commentService;

    @Autowired
    QuestionService questionService;

    public void insert(Notification notification){
        notificationMapper.insert(notification);
    }

    //得到用户读消息,id是自己的id
    public PageDTO getList(Integer id, Integer page, Integer size) {

        PageHelper.startPage(page,size,"gmt_create desc");
        //得到分页的数据,根据接收人的id
        List<Notification> currentNotification =  notificationMapper.getListByUserID(id);
        //得到分页信息（这个分页信息不完整，不是我们需要的）
        PageInfo<Notification> pageInfo = new PageInfo<>(currentNotification);





        //结合用户信息
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        //在聊天记录中查询用户
        for(Notification notification:currentNotification){

            //得到该消息发起的用户
            User user = userMapper.findById(notification.getNotifier());

            String title;
            String typeString=null;
            //要得到问题的标题
            if(notification.getType()==NotificationEnum.REPLY_QUESTION.getStatus()){

                //该消息是针对问题的
                QuestionDTO question = questionService.getById(notification.getOuter_id());
                title = question.getTitle();
                typeString = "回答了：";
            }else {

                //该消息是针对回复的，那么需要得到回复，再得到问题
                Comment comment = commentService.getCommentById(notification.getOuter_id());
                QuestionDTO question = questionService.getById(comment.getParent_id());
                title = question.getTitle();
                typeString = "评论了你在该中的回复回复：";
            }



            //把得到NotificationDTO
            NotificationDTO notificationDTO= new NotificationDTO();
            notificationDTO.setGmt_create(notification.getGmt_create());
            notificationDTO.setId(notification.getId());
            notificationDTO.setNotifier(user);
            notificationDTO.setType(typeString);
            notificationDTO.setOuterTitle(title);
            notificationDTO.setStatus(notification.getStatus());
            notificationDTOList.add(notificationDTO);
        }


        PageDTO pageDTO = new PageDTO();
        pageDTO.setNotificationDTOList(notificationDTOList);
        pageDTO.setFirstPage(pageInfo.isIsFirstPage());
        pageDTO.setLastPage(pageInfo.isIsLastPage());
        pageDTO.setNextPage(pageInfo.isHasNextPage());
        pageDTO.setPrePage(pageInfo.isHasPreviousPage());
        pageDTO.setCurrentPage(pageInfo.getPageNum());
        pageDTO.setTotalPage(pageInfo.getPages());
        pageDTO.setTotalNum(pageInfo.getTotal());

        pageDTO.setPages(pageInfo.getNavigatepageNums());

        return  pageDTO;

    }

    public Integer unReadCount(Integer id){
        return notificationMapper.getUnReadCount(id);
    }

    public Notification getById(Integer id) {
        Notification notification = notificationMapper.getById(id);
        return  notification;
    }


    public void update(Notification notification){
        notificationMapper.update(notification);
    }

}
