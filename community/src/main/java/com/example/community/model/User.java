//model中放不用网络传输的类
package com.example.community.model;


import lombok.Data;

@Data
public class User {

    private Integer id;

    private String name;
    private String passwd;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String avatar_url;
    private Integer github_id;

}
