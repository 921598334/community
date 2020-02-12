package com.example.community.cache;

import com.example.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagCache {

    public static List<TagDTO> get(){

        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO tagDTO = new TagDTO("开发语言",Arrays.asList("java","python","c#","c","php"));
        TagDTO tagDTO1 = new TagDTO("平台",Arrays.asList("win","linux","mac"));



        tagDTOS.add(tagDTO1);
        tagDTOS.add(tagDTO);
        return tagDTOS;
    }

}
