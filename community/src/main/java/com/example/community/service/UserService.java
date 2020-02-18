package com.example.community.service;

import com.example.community.mapper.GithubUserMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    GithubUserMapper githubUserMapper;


    //创建或者更新用户，如果能在数据库中查询到用户，就更新token，否则就插入
    public User createOrUpdate(User newUser) {

        //注册时没有ID，所以直接创建
        if(newUser.getId()==null)
        {
            userMapper.insertUser(newUser);
            return newUser;
        }




       User oldUser = userMapper.findById(newUser.getId());

       if(oldUser==null){

           //不存在需要插入
           userMapper.insertUser(newUser);

       }else{

           userMapper.update(newUser);
       }

       return newUser;
    }

    //登陆时判断用户的用户名和密码时候相同,如果相同返回true
    public User checkUser(User user){
        return userMapper.check(user);
    }

    //根据用户名寻找用户，在注册时用于判断当前用户是否存在
    public User findByUserName(String userName){
        return userMapper.findByUserName(userName);
    }


    public User findById(Integer user_id) {
        return userMapper.findById(user_id);
    }
}
