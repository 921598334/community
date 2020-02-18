package com.example.community.mapper;

import com.example.community.model.User;
import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
public interface UserMapper {


    //如果方法中是一个自定义到类，可以不用Param
    @Insert("insert into user(name,passwd,token,gmt_create,gmt_modified,avatar_url) " +
                  "values (#{name},#{passwd},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insertUser(User user);



    //查到的数据可以直接封装为类的条件是类的属性顺序、名称（包括构造函数的参数列表）要与数据库的属性、名称顺序一致
    @Select("select * from user where token= #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id= #{id}")
    User findById(@Param("id") Integer id);

    //这个用来检查用户是否存在
    @Select("select * from user where name= #{name}")
    User findByUserName(@Param("name") String name);

    //更新用户
    @Update("UPDATE user SET name = #{name},passwd=#{passwd},token=#{token},gmt_modified=#{gmt_modified},avatar_url=#{avatar_url} where id = #{id}")
    void update(User user);

    //检查用户名和密码是否匹配
    @Select("select * from user where name= #{name} and passwd = #{passwd}")
    User check(User user);
}
