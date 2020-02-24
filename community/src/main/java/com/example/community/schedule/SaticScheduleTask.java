package com.example.community.schedule;

import com.example.community.cache.HotTagCache;
import com.example.community.model.Question;
import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {


    @Autowired
    QuestionService questionService;

    //3.添加定时任务
    //@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：60秒
    @Scheduled(fixedRate=60000)
    private void configureTasks() {


        System.out.println("定时任务启动");

        HotTagCache.tags.clear();


        //得到所有问题
        List<Question> questionList = questionService.getAll();

        for(Question question:questionList){

            String[] tags = question.getTag().split(",");

            for(String tag:tags){

                //如果热门标签缓存中没有包含该标签，就计算该标签的热度
                if(!HotTagCache.tags.containsKey(tag)){

                    List<Question> questions = questionService.findAllQuestionsByTag(tag);

                    //热度为该标签下所有问题的评论数和
                    Integer hotPoint = 0;
                    for(Question question1:questions){
                        hotPoint+=question1.getComment_count();
                    }

                    HotTagCache.tags.put(tag,hotPoint);
                }

            }

        }


        //开始寻找热门标签
        HotTagCache.Sort();



    }
}