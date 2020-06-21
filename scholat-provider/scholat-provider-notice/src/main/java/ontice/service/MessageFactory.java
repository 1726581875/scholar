package ontice.service;

import cn.scholat.service.CourseUserServiceApi;
import lombok.extern.slf4j.Slf4j;
import ontice.pojo.Notice;
import org.scholat.common.pojo.CourseUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class MessageFactory {

     @Autowired
     private CourseUserServiceApi courseUserApi;


     public List<Notice> getHomeWorkMessage(Integer sendId , Integer courseId){
          List<Notice> noticeList = new ArrayList<>();
          List<CourseUserInfo> courseUserList = courseUserApi.findAllCourseUserByCourseId(courseId);
          courseUserList.forEach(e -> {
               noticeList.add(new Notice()
                       .setSendId(sendId)
                       .setAcceptId(e.getUserId())
                       .setNoticeType(1)
                       .setNoticeContent("hello 你有新作业要提交~~")
                       .setNoticeFlag(0));
          });
        return noticeList;
     }







}
