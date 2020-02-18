package com.example.community.service;

import com.example.community.mapper.GithubUserMapper;
import com.example.community.model.GithubUser;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitHubUserService {

    @Autowired
    GithubUserMapper githubUserMapper;






    public GithubUser findById(Integer id){
        return githubUserMapper.findById(id);
    }

    //这个用来检查github用户是否存在
    public GithubUser findByAcountId(String account_id){
        return githubUserMapper.findByAcountId(account_id);
    }

    public GithubUser createOrUpdate(GithubUser user) {

        //注册时没有ID，所以直接创建
        if(user.getId()==null)
        {
            githubUserMapper.insertUser(user);
            return user;
        }


        GithubUser newUser = githubUserMapper.findById(user.getId());

        if(newUser==null){

            //不存在需要插入
            githubUserMapper.insertUser(newUser);

        }else{

            //存在到话需要更新外key
            newUser.setUser_id(user.getUser_id());

            githubUserMapper.update(user);
        }

        return user;
    }
}
