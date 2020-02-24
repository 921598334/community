package com.example.community.cache;

import com.example.community.model.HotTag;

import java.util.*;



class HotTagDescendingComparator implements Comparator<HotTag> {

    @Override
    public int compare(HotTag o1, HotTag o2) {
        return -o2.getHotPoint() + o1.getHotPoint();
    }

}


public class HotTagCache {

    public static Map<String,Integer> tags  = new HashMap();
    public static List<String> tagResult = new ArrayList<>();
    public static Stack<String> tmp = new Stack();



    //利用优先级队列进行排序
    public static void Sort(){

        tagResult.clear();
        tmp.clear();

        //优先级队列就是一个堆
        PriorityQueue<HotTag> hotTags = new PriorityQueue<>(new HotTagDescendingComparator());

        //取前5个最热门的标签
        tags.forEach((name,hotPoint)->{

            HotTag hotTag = new HotTag();
            hotTag.setName(name);
            hotTag.setHotPoint(hotPoint);

            if(hotTags.size()<5){
                hotTags.add(hotTag);
            }else {

                //在5个缓存中最小的标签
                HotTag minHotTag = hotTags.peek();
                if(minHotTag.getHotPoint()<hotTag.getHotPoint()){
                    hotTags.poll();
                    hotTags.add(hotTag);
                }

            }

        });


        //打印出来
        HotTag hotTag = hotTags.poll();

        while (hotTag!=null){

            tmp.add(hotTag.getName());
            hotTag = hotTags.poll();
        }


        //tmpz中的内容是倒序的，需要重新处理
        Integer size = tmp.size();
        for(int i=0;i<size;i++){
            tagResult.add(tmp.pop());
        }



        System.out.println();


    }

}
