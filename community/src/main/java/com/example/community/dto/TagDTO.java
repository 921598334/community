package com.example.community.dto;

import lombok.Data;

import java.util.List;

//用来表示某一个类下的所有标签
@Data
public class TagDTO {

    private String className;
    private List<String> tags;


    public TagDTO(String className, List<String> tags) {
        this.className = className;
        this.tags = tags;
    }
}
