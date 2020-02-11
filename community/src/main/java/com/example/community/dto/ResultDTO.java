package com.example.community.dto;


import lombok.Data;

import javax.xml.soap.SAAJResult;

//这个用来表示Json的返回信息
@Data
public class ResultDTO {

    //类似于http状态吗
    private Integer code;
    private String message;

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



}
