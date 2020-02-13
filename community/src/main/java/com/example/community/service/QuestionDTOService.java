package com.example.community.service;

import com.example.community.advice.CustomizeException;
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
public class QuestionDTOService {


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

        String orderBy = "gmt_modified" + " desc";

        PageHelper.startPage(page,size,orderBy);
        //得到分页的数据
        List<Question> currentQuestion =  questionMapper.getList();
        //得到分页信息（这个分页信息不完整，不是我们需要的）
        PageInfo<Question> pageInfo = new PageInfo<>(currentQuestion);


        //结合用户信息
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //在聊天记录中查询用户
        for(Question question:currentQuestion){

            User user = userMapper.findById(question.getCreator());

            //表示这篇文章没有用户创建（或者该用户已经不存在数据库中了）
            if(user == null){
                System.out.println("有篇文章没有用户创建");
                continue;
            }

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


    //根据用户id得到当前用户的问题,并且分页显示
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


    //根据问题id得到一个问题，并且和用户绑定为QuestionDTO
    public QuestionDTO getById(Integer id){

        Question question = questionMapper.getById(id);

        //如果没有找到，就跑出异常交给处理异常的类进行处理然后返回页面
        if(question==null){
            throw  new CustomizeException("这个问题不存在");
        }


        //在聊天记录中查询用户
        User user = userMapper.findById(question.getCreator());

        if(user==null){
            throw  new CustomizeException("这篇文章的用户不存在");
        }

        //把QuestionDTO和User中的相关属性拷贝到QuestionDTO中
        QuestionDTO questionDTO= new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);


        return  questionDTO;
    }


    //添加阅读数
    public void addView(QuestionDTO questionDTO){

        questionDTO.setView_count(questionDTO.getView_count()+1);

        Question question = new Question(questionDTO.getTitle(),questionDTO.getDescription(),questionDTO.getTag(),questionDTO.getGmt_create(),questionDTO.getGmt_modified(),questionDTO.getCreator(),questionDTO.getView_count(),questionDTO.getComment_count(),questionDTO.getLike_count());
        question.setId(questionDTO.getId());

        questionMapper.update(question);

    }
}
