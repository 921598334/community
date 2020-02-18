package com.example.community.mapper;

import com.example.community.model.GithubUser;
import com.example.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GithubUserMapper {



    //如果方法中是一个自定义到类，可以不用Param
    @Insert("insert into githubuser(name,account_id) values (#{name},#{account_id})")
    void insertUser(GithubUser user);



    @Select("select * from githubuser where id= #{id}")
    GithubUser findById(@Param("id") Integer id);

    //这个用来检查github用户是否存在
    @Select("select * from githubuser where account_id= #{account_id}")
    GithubUser findByAcountId(@Param("account_id") String account_id);


    @Update("UPDATE githubuser SET account_id = #{account_id},name=#{name},user_id=#{user_id}")
    void update(GithubUser user);
}
