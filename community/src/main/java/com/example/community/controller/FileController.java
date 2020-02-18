package com.example.community.controller;


import com.example.community.dto.FileDTO;
import com.example.community.model.UploadImageResModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//上传图片后需要返回的结果
@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/uploadImage")
    @ResponseBody
    public UploadImageResModel uploadImage(@RequestParam("upload") MultipartFile multipartFile) {
        UploadImageResModel res = new UploadImageResModel();
        res.setUploaded(0);

        if (multipartFile == null || multipartFile.isEmpty())
            return res;

        //生成新的文件名及存储位置
        String fileName = multipartFile.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString()
                .replaceAll("-", "")
                .concat(fileName.substring(fileName.lastIndexOf(".")));


        String fullPath = System.getProperty("user.dir")+"/src/main/resources/static/upload/".concat(newFileName);

        try {
            File target = new File(fullPath);
            if (!target.getParentFile().exists()) { //判断文件父目录是否存在
                target.getParentFile().mkdirs();
            }

            multipartFile.transferTo(target);


            String imgUrl = "/upload/".concat(newFileName);

            res.setUploaded(1);
            res.setFileName(fileName);
            res.setUrl(imgUrl);
            return res;

        } catch (IOException ex) {
            logger.error("上传图片异常", ex);
        }

        return res;
    }

}