package com.example.community.mapper;

import com.example.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

//    User的属性
//    private Integer id;
//    private String name;
//    private String accountId;
//    private String token;
//    private Long gmtCreate;
//    private Long gmtModitied;

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModitied})")
    void InsertUser(User user);



}
