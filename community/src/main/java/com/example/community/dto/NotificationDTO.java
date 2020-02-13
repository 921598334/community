package com.example.community.dto;

import com.example.community.model.User;
import lombok.Data;


@Data
public class NotificationDTO {

    Integer id;
    Long gmt_create;
    Integer status;
    //该评论或者点赞的发布人
    User notifier;
    String outerTitle;
    String type;
}
