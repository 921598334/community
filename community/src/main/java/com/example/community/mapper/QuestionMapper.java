package com.example.community.mapper;


import com.example.community.model.Comment;
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


    //得到问题的所有回复,输入是问题的id，得到的是所有回复,type1是回复
    @Select("select * from comment where parent_id= #{id} and type=1")
    List<Comment> findCommentsById(@Param("id") Integer id);


    //根据标签得到问题,通过正则表达式的方式要得到输入的tag满足心如：xxx|xxxx|xxx|xx,这样可以在数据的标签中匹配至包括xxx字段的标签,在得到的问题中排除该问题本身
    @Select("select * from question where tag regexp #{tag} and id!=#{id}")
    List<Question> findQuestionsByTag(@Param("tag") String tag,@Param("id") Integer id);


}
