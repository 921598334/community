package com.example.community.model;

import lombok.Data;


@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;



    public Question( String title, String description, String tag, Long gmt_create, Long gmt_modified, Integer creator, Integer view_count, Integer comment_count, Integer like_count) {

        this.title = title;
        this.description = description;
        this.tag = tag;
        this.gmt_create = gmt_create;
        this.gmt_modified = gmt_modified;
        this.creator = creator;
        this.view_count = view_count;
        this.comment_count = comment_count;
        this.like_count = like_count;
    }





}
