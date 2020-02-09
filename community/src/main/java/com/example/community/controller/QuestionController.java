package com.example.community.controller;


import com.example.community.dto.QuestionDTO;
import com.example.community.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//点击问题时显示问题描述的界面
@Controller
public class QuestionController {

    @Autowired
    QuestionDTOService questionDTOService;

    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name = "id") Integer id,
            Model model
    ){


        QuestionDTO questionDTO = questionDTOService.getById(id);

        model.addAttribute("question",questionDTO);

        return "question";

    }
}
