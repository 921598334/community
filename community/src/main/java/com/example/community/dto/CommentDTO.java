package com.example.community.dto;

import com.example.community.model.User;
import lombok.Data;

import java.util.List;

//这个类比较混乱，代表了问题的回复，或者回复的评论，其中评论是2，回复是1
@Data
public class CommentDTO {

    private Integer id;
    private Integer parent_id;
    private Integer type;
    private Integer commentator;
    private Long gmt_create;
    private Long gmt_modified;
    private Long like_count;
    private String comment;
    private User user;

    //该回复的评论数
    private List<CommentDTO> comments;
}
