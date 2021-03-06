package com.example.community.controller;


import com.example.community.dto.CommentDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.model.Comment;
import com.example.community.model.Question;

import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


//点击问题时显示问题描述的界面，包括显示该问题的评论
@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;



    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name = "id") Integer id,
            Model model
    ){


        QuestionDTO questionDTO = questionService.getById(id);

        //添加观看人数
        questionService.addView(questionDTO);

        model.addAttribute("question",questionDTO);


        //需要根据当前的问题id得到所有的回复
        List<CommentDTO> comments = questionService.getCommentById(id);
        //得到回复下的评论数
        model.addAttribute("comments",comments);


        //根据当前问题的标签得到所有的相关问题
        List<Question> relatedQuestions = questionService.findQuestionsByTag(questionDTO.getTag(),questionDTO.getId());
        model.addAttribute("relatedQuestions",relatedQuestions);

        return "question";

    }
}
