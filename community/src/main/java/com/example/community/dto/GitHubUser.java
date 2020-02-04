package com.example.community.dto;

public class GitHubUser {

    private String name;
    //githun上的唯一标示
    private Long id;
    private String bio;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public GitHubUser(String name, Long id, String bio) {
        this.name = name;
        this.id = id;
        this.bio = bio;
    }
}
