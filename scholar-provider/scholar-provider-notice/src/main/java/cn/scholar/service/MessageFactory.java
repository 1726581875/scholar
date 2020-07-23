package cn.scholar.service;

import cn.scholar.course.service.api.CourseUserServiceApi;
import cn.scholar.common.pojo.CourseUserInfo;
import lombok.extern.slf4j.Slf4j;
import cn.scholar.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class MessageFactory {

     @Autowired
     private CourseUserServiceApi courseUserApi;


     public List<Notice> getHomeWorkMessage(Integer sendId , Integer courseId,String message){
          List<Notice> noticeList = new ArrayList<>();
          List<CourseUserInfo> courseUserList = courseUserApi.findAllCourseUserByCourseId(courseId);
          courseUserList.forEach(e -> {
               noticeList.add(new Notice()
                       .setSendId(sendId)
                       .setAcceptId(e.getUserId())
                       .setNoticeType(1)
                       .setNoticeContent(message)
                       .setNoticeFlag(0));
          });
        return noticeList;
     }

     /**
      * 构造一个条简单消息
      * @param sendId
      * @param acceptId
      * @param message
      * @return
      */
     public Notice getSimpleMessage(Integer sendId , Integer acceptId,String message){

          return  new Notice()
                  .setSendId(sendId)
                  .setAcceptId(acceptId)
                  .setNoticeType(2)//类型2，个人信息
                  .setNoticeContent(message)
                  .setNoticeFlag(0);//未读
     }




}
