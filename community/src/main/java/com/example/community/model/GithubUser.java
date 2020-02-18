//model中放不用网络传输的类
package com.example.community.model;


import lombok.Data;

@Data
public class GithubUser {

    //giethub用户id
    private Integer id;
    //giethub用户id
    private String account_id;
    private String name;
   //本地系统的用户id
    private Integer user_id;


}
