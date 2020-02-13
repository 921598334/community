package com.example.community.controller;


import com.example.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//上传图片后需要返回的结果
@Controller
public class FileController {

    @GetMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(){

        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("www.baidu.com");
        return  fileDTO;
    }

}
