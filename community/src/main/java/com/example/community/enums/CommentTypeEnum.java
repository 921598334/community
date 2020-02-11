package com.example.community.enums;

//评论类型(是评论还是回复)
public enum  CommentTypeEnum {

    //回复
    QUESRION(1),
    //评论
    COMMENT(2);

    private  Integer type;

    public Integer getType(){
        return type;
    }

    CommentTypeEnum(Integer type){
        this.type = type;
    }
}
