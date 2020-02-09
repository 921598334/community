package com.example.community.service;


import com.example.community.mapper.QuestionMapper;
import com.example.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    public void createOrUpdate(Question newQuestion){


        Question question = questionMapper.getById(newQuestion.getId());

        if(question==null){
            //不存在需要插入
            questionMapper.create(newQuestion);

        }else{

            //除了创建时间外，其他都要更新
            newQuestion.setGmt_create(question.getGmt_create());

            questionMapper.update(newQuestion);
        }


    }


}
