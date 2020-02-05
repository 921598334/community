//model中放不用网络传输的类
package com.example.community.model;

public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModitied;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModitied() {
        return gmtModitied;
    }

    public void setGmtModitied(Long gmtModitied) {
        this.gmtModitied = gmtModitied;
    }

    public User( String name, String accountId, String token, Long gmtCreate, Long gmtModitied) {


        this.name = name;
        this.accountId = accountId;
        this.token = token;
        this.gmtCreate = gmtCreate;
        this.gmtModitied = gmtModitied;
    }

    public User() {
    }
}
