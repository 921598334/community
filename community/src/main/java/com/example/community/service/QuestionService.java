package com.example.community.service;


import com.example.community.advice.CustomizeException;
import com.example.community.dto.CommentDTO;
import com.example.community.dto.PageDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Comment;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.example.community.provider.HTMLProccess;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    HTMLProccess htmlProccess;

    @Value("${showLength}")
    private Integer showLength;


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


    //根据当前问题的tag得到相关的问题,
    public List<Question> findAllQuestionsByTag(String tag){

        String reTag = tag.replace(',','|');
        return questionMapper.findAllQuestionsByTag(reTag);
    }



    //同时根据标签和搜索内容进行查询
    public PageDTO getListByTagAndSearch(Integer page, Integer size,String tag,String search) {


        String orderBy = "gmt_modified" + " desc";


        List<Question> currentQuestion = null;

        PageHelper.startPage(page,size,orderBy);

        //判读当前的查询方式，如果标签为空，且搜索为空，那就是全部查询
        if((search==null || search.equals(""))&&(tag==null || tag.equals(""))){
            currentQuestion =  questionMapper.getList();
        }
        else if((search!=null || !search.equals(""))&&(tag==null || tag.equals(""))){
            //搜索不为空，同时标签为空
            String  searchTmp = search.replace(" ","|");
            currentQuestion =  questionMapper.getListRegexp(searchTmp);
        }else if((search==null || search.equals(""))&&(tag!=null || !tag.equals(""))){
            //标签不为空，同时搜索为空
            String reTag = tag.replace(',','|');
            currentQuestion = questionMapper.findAllQuestionsByTag(reTag);
        }else {
            //既有标签又有搜索
            String  searchTmp = search.replace(" ","|");
            String reTag = tag.replace(',','|');
            currentQuestion = questionMapper.findAllQuestionsByTagAndSearch(reTag,searchTmp);
        }




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


            //得到正文内容，然后删除html的部分
            String descript = questionDTO.getDescription();
            descript = htmlProccess.delHTMLTag(descript);

            //去前n个字符
            Integer length = descript.length();
            if(length>showLength){
                descript = descript.substring(0,showLength-1);
                descript+="......";
            }

            questionDTO.setDescription(descript);

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







    //分页显示,当点击了标签时调用，得到标签下当所有问题
    public PageDTO getListByTag(Integer page, Integer size,String tag) {


        List<Question> currentQuestion = null;

        PageHelper.startPage(page,size);
        //得到分页的数据,其中需要模糊匹配
        currentQuestion = questionMapper.findAllQuestionsByTag(tag);


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


            //得到正文内容，然后删除html的部分
            String descript = questionDTO.getDescription();
            descript = htmlProccess.delHTMLTag(descript);

            //去前n个字符
            Integer length = descript.length();
            if(length>showLength){
                descript = descript.substring(0,showLength-1);
                descript+="......";
            }

            questionDTO.setDescription(descript);

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






    public List<Question> getAll(){
        return questionMapper.getList();
    }




    //分页显示,其中会对搜索进行模糊匹配
    public PageDTO getList(Integer page, Integer size, String search) {

        String orderBy = "gmt_modified" + " desc";


        List<Question> currentQuestion = null;

        PageHelper.startPage(page,size,orderBy);
        //得到分页的数据,其中需要模糊匹配
        if(search!=null && !search.equals("")) {
            String  searchTmp = search.replace(" ","|");
            currentQuestion =  questionMapper.getListRegexp(searchTmp);
        }
        else {
            currentQuestion =  questionMapper.getList();
        }


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


            //得到正文内容，然后删除html的部分
            String descript = questionDTO.getDescription();
            descript = htmlProccess.delHTMLTag(descript);

            //去前n个字符
            Integer length = descript.length();
            if(length>showLength){
                descript = descript.substring(0,showLength-1);
                descript+="......";
            }

            questionDTO.setDescription(descript);

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


            //得到正文内容，然后删除html的部分
            String descript = questionDTO.getDescription();
            descript = htmlProccess.delHTMLTag(descript);

            //去前n个字符
            Integer length = descript.length();
            if(length>showLength){
                descript = descript.substring(0,showLength-1);
                descript+="......";
            }

            questionDTO.setDescription(descript);




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
