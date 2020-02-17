package com.example.community.controller;


import ch.qos.logback.core.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
public class UeditorController {


    @RequestMapping(value = "/utf8-jsp/config")
    @ResponseBody
    public String uploadConfig() {
        String s = "{\n" +
                "            \"imageActionName\": \"uploadimage\",\n" +
                "                \"imageFieldName\": \"upfile\", \n" +
                "                \"imageMaxSize\": 2048000, \n" +
                "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "                \"imageCompressEnable\": true, \n" +
                "                \"imageCompressBorder\": 1600, \n" +
                "                \"imageInsertAlign\": \"none\", \n" +
                "                \"imageUrlPrefix\": \"\",\n" +
                "                \"imagePathFormat\": \"/utf8-jsp/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\" }";
        return s;
    }






    /**
     * 自定义文件上传接口，我这里直接调用七牛云上传
     * @param upfile upfile参数保证与config.json中imageFieldName设置一致
     * @return 返回数据格式，是ueditor规定的格式
     * @throws Exception
     */
    @RequestMapping(value = "/uploadfile")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile upfile)
            throws Exception {




        Map<String, Object> rs = new HashMap<String, Object>();
        String originalFileName = "";
        try {
            if (!upfile.isEmpty()) {

                //文件本身的名称
                originalFileName = upfile.getOriginalFilename();


                //生成新的文件名及存储位置
                String newFileName = UUID.randomUUID().toString()
                        .replaceAll("-", "")
                        .concat(originalFileName.substring(originalFileName.lastIndexOf(".")));


                //目标存储路径
                String fullPath = System.getProperty("user.dir")+"/src/main/resources/static/upload/".concat(newFileName);

                //判断目标存储路径是否存在
                File target = new File(fullPath);
                if (!target.getParentFile().exists()) {
                    target.getParentFile().mkdirs();
                }


                upfile.transferTo(target);

                String imgUrl = "/upload/".concat(newFileName);


                System.out.println(imgUrl);
                rs.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
                rs.put("url", imgUrl); // 能访问到你现在图片的路径
                rs.put("title", originalFileName);
                rs.put("original", originalFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rs.put("state", e.getMessage());
            rs.put("url", "");
            rs.put("title", "");
            rs.put("original", "");
        }
        return rs;
    }

}
