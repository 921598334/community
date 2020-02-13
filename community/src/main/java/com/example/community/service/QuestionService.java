package com.example.community.service;


import com.example.community.advice.CustomizeException;
import com.example.community.dto.CommentDTO;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Comment;
import com.example.community.model.Question;
import com.example.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentService commentService;

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




    //添加评论数
    public void addComment(Question question){

        question.setComment_count(question.getComment_count()+1);


        questionMapper.update(question);

    }



    //根据当前问题的ID，得到当前问题下的所有回复，与回复所绑定的评论
    public List<CommentDTO> getCommentById(Integer id){

        List<CommentDTO> commentDTOList = new ArrayList<>();

        //得到所有回复
        List<Comment> comments = questionMapper.findCommentsById(id);

        //把问题和提出问题的人进行绑定
        //根据comment的创建人id寻找创建人
        for(Comment comment:comments){

            User user = userMapper.findById(comment.getCommentator());

            if(user==null){
                throw  new CustomizeException("这个回复的用户不存在");
            }

            CommentDTO commentDTO= new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }

        //开始绑定该回复下的评论
        for(CommentDTO commentDTO:commentDTOList){

            List<CommentDTO> commentList = commentService.getComments(commentDTO.getId());
            commentDTO.setComments(commentList);
        }

        return commentDTOList;

    }


    //根据当前问题的tag得到相关的问题,但是排除该id对应的问题
    public List<Question> findQuestionsByTag(String tag,Integer id){

        String reTag = tag.replace(',','|');
        return questionMapper.findQuestionsByTag(reTag,id);
    }


    public Question findQuestionById(Integer id){

        return  questionMapper.getById(id);

    }
}
