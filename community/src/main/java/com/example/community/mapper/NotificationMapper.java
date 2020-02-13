package com.example.community.mapper;

import com.example.community.model.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotificationMapper {

     @Insert("insert into notification (notifier,receiver,outer_id,type,gmt_create,status) values (#{notifier},#{receiver},#{outer_id},#{type},#{gmt_create},#{status})")
     void insert(Notification notification);

     //根据用户的id得到所有的通知
     @Select("select * from notification where receiver=#{id}")
     List<Notification> getListByUserID(Integer id);

     //得到某个用户没有读读消息
     @Select("select count(1) from notification where receiver=#{id} and status=0")
     Integer getUnReadCount(Integer id);

     //根据通知的id得到通知
     @Select("select * from notification where id=#{id}")
     Notification getById(Integer id);

     //更新通知的状态
     @Select("UPDATE notification SET notifier = #{notifier},receiver=#{receiver},outer_id=#{outer_id},type=#{type},status=#{status},gmt_create=#{gmt_create} where id = #{id}")
     void update(Notification notification);
}
