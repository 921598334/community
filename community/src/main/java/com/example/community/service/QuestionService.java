package com.example.community.service;


import com.example.community.dto.PageDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;


//单需要Mapper中的多个对象进行组装使用的时候，可以在Service进行，假如service标签后，可以用Autowired进行注入
@Service
public class QuestionService {


    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;


    //得到所有的记录
    public List<QuestionDTO> getList() {

        List<Question> questions = questionMapper.getList();

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        //在聊天记录中查询用户
        for(Question question:questions){

            User user = userMapper.findById(question.getCreator());

            //把QuestionDTO和User中的相关属性拷贝到QuestionDTO中
            QuestionDTO questionDTO= new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }

        return  questionDTOList;
    }


    //分页显示
    public PageDTO getList(Integer page, Integer size) {

        PageHelper.startPage(page,size);
        //得到分页的数据
        List<Question> currentQuestion =  questionMapper.getList();
        //得到分页信息（这个分页信息不完整，不是我们需要的）
        PageInfo<Question> pageInfo = new PageInfo<>(currentQuestion);


        //结合用户信息
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //在聊天记录中查询用户
        for(Question question:currentQuestion){

            User user = userMapper.findById(question.getCreator());

            //把QuestionDTO和User中的相关属性拷贝到QuestionDTO中
            QuestionDTO questionDTO= new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }



        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(questionDTOList);
        pageDTO.setFirstPage(pageInfo.isIsFirstPage());
        pageDTO.setLastPage(pageInfo.isIsLastPage());
        pageDTO.setNextPage(pageInfo.isHasNextPage());
        pageDTO.setPrePage(pageInfo.isHasPreviousPage());
        pageDTO.setCurrentPage(pageInfo.getPageNum());
        pageDTO.setTotalPage(pageInfo.getPages());




        pageDTO.setPages(pageInfo.getNavigatepageNums());

        return  pageDTO;

    }


    //根据用户id得到当前用户的问题
    public PageDTO getList(Integer id,Integer page,Integer size) {

        PageHelper.startPage(page,size);
        //得到分页的数据
        List<Question> currentQuestion =  questionMapper.getListByUserID(id);
        //得到分页信息（这个分页信息不完整，不是我们需要的）
        PageInfo<Question> pageInfo = new PageInfo<>(currentQuestion);


        //结合用户信息
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //在聊天记录中查询用户
        for(Question question:currentQuestion){

            User user = userMapper.findById(question.getCreator());

            //把QuestionDTO和User中的相关属性拷贝到QuestionDTO中
            QuestionDTO questionDTO= new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }



        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(questionDTOList);
        pageDTO.setFirstPage(pageInfo.isIsFirstPage());
        pageDTO.setLastPage(pageInfo.isIsLastPage());
        pageDTO.setNextPage(pageInfo.isHasNextPage());
        pageDTO.setPrePage(pageInfo.isHasPreviousPage());
        pageDTO.setCurrentPage(pageInfo.getPageNum());
        pageDTO.setTotalPage(pageInfo.getPages());




        pageDTO.setPages(pageInfo.getNavigatepageNums());

        return  pageDTO;

    }
}
