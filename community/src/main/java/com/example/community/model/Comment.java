package com.example.community.model;

import lombok.Data;

@Data
public class Comment {

    private Integer id;
    //如果是问题的回复，那么parent_id就是问题，如果是回复的评论，那parent_id就是Comment的id（在数据库中，类没有）
    private Integer parent_id;
    private Integer type;
    private Integer commentator;
    private Long gmt_create;
    private Long gmt_modified;
    private Long like_count;
    private String comment;
}
