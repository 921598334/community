package com.example.community.mapper;

import com.example.community.model.User;
import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
public interface UserMapper {



//    User的属性
//    private Integer id;
//    private String name;
//    private String accountId;
//    private String token;
//    private Long gmtCreate;
//    private Long gmtModitied;


    //如果方法中是一个自定义到类，可以不用Param
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModitied})")
    void InsertUser(User user);

    //如果方法中不是一个自定义到类，要Param
    @Select("select * from user where token= #{token}")
    Map<String,Object> findByToken(@Param("token") String token);


}
