package com.example.community.mapper;

import com.example.community.dto.CommentDTO;

import com.example.community.model.Comment;
import com.example.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public  interface CommentMapper {

    //如果方法中是一个自定义到类，可以不用Param
    @Insert("insert into comment(parent_id,type,commentator,gmt_create,gmt_modified,like_count,comment) values (#{parent_id},#{type},#{commentator},#{gmt_create},#{gmt_modified},#{like_count},#{comment})")
    void insertComment(Comment comment);


    //当前是回复的评论，需要根据评论的父id找到回复，其中评论是2，回复是1
    @Select("select * from comment where id= #{parent_id} and type=1")
    Comment findCommentByParentId(@Param("parent_id") Integer parent_id);


    //得到回复下的所有评论，根据该回复的id
    @Select("select * from comment where parent_id= #{id} and type=2")
    List<Comment> getComments(@Param("id") Integer id);

    //根据id得到某一个回复或者评论
    @Select("select * from comment where id= #{id}")
    Comment getCommentById(@Param("id") Integer id);


    //根据问题的id得到该问题的所有回复,输入的是问题的id，输出为所有回复
    @Select("select * from question where id= #{id}")
    Question findQuestionByParentId(Integer id);
}
