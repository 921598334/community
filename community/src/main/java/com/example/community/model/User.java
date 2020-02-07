//model中放不用网络传输的类
package com.example.community.model;


import lombok.Data;

@Data
public class User {

    private Integer id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String avatar_url;




    public User( String name, String account_id, String token, Long gmt_create, Long gmt_modified,String avatar_url) {

        this.name = name;
        this.account_id = account_id;
        this.token = token;
        this.gmt_create = gmt_create;
        this.gmt_modified = gmt_modified;
        this.avatar_url = avatar_url;
    }


    public User(){}
}
