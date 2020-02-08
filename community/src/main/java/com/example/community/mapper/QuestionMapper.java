package com.example.community.mapper;


import com.example.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,comment_count,view_count,like_count,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{comment_count},#{view_count},#{like_count},#{creator},#{tag})")
     void create(Question question);


    @Select("select * from question")
    List<Question> getList();


    //根据用户id得到该用户的问题
    @Select("select * from question where creator=#{id}")
    List<Question> getListByUserID(@Param("id") Integer id);
}
