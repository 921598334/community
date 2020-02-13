package com.example.community.dto;


import com.example.community.model.User;
import lombok.Data;


//在主界面说要显示的用户问题
@Data
public class QuestionDTO {

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
    private User user;




}
