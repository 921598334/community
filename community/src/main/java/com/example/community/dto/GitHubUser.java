package com.example.community.dto;


import lombok.Data;

@Data
public class GitHubUser {

    private String name;
    //githun上的唯一标示
    private Long id;
    //简介
    private String bio;

    //fastJson插件可以自动把下划线映射为驼峰命令，所以可以不用修改为与数据库对应的下划线命令
    private String avatarUrl;


}
