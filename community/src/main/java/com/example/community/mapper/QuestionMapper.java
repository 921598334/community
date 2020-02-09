package com.example.community.mapper;


import com.example.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,comment_count,view_count,like_count,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{comment_count},#{view_count},#{like_count},#{creator},#{tag})")
     void create(Question question);


    //更新问题
    @Update("UPDATE question SET title = #{title},description=#{description},gmt_modified=#{gmt_modified},comment_count=#{comment_count},view_count=#{view_count},like_count=#{like_count},tag=#{tag} where id = #{id}")
    void update(Question question);



    @Select("select * from question")
    List<Question> getList();


    //根据用户id得到该用户的问题
    @Select("select * from question where creator=#{id}")
    List<Question> getListByUserID(@Param("id") Integer id);


    //根据问题的id得到数据
    @Select("select * from question where id=#{id}")
    Question getById(Integer id);
}
