package com.example.community.service;

import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    //创建或者更新用户，如果能在数据库中查询到github用户到account_id存在，就更新token，否则就插入
    public User createOrUpdate(User newUser) {

       User user = userMapper.findByAcountId(newUser.getAccount_id());

       if(user==null){

           //不存在需要插入
           userMapper.insertUser(newUser);

       }else{

           //存在到话需要更新token和修改时间,头像,姓名等
           user.setToken(newUser.getToken());
           user.setGmt_modified(newUser.getGmt_modified());
           user.setAvatar_url(newUser.getAvatar_url());
           user.setName(newUser.getName());

           userMapper.update(user);
       }

       return user;
    }
}
