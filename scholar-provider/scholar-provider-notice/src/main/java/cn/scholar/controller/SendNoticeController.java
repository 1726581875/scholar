package cn.scholar.controller;

import cn.scholar.course.service.api.CourseUserServiceApi;
import cn.scholar.common.pojo.CourseUserInfo;
import cn.scholar.service.MessageFactory;
import lombok.extern.slf4j.Slf4j;
import cn.scholar.service.MyWebSoket;
import cn.scholar.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class SendNoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private MyWebSoket myWebSoket;

    @Resource
    private CourseUserServiceApi courseUserApi;

    @Autowired
    private MessageFactory messageFactory;

    /**
     * 给全站在线用户发通知
     * @param message
     */
    @PostMapping("/send/to/all")
    public void sendMessageToAll(String message){
        log.info("[通知服务]给所有在线用户发送信息，message======>{}",message);
        myWebSoket.sendMessage(message);

    }

    /**
     * 对某个班级发通知
     */
    @PostMapping("/send/to/course/user")
    public void sendMessageToCourseUser(Integer sendId, Integer courseId,String message){
        log.info("[通知服务] sendMessage======>{}",message);
        //查出该课程所有成员
        List<CourseUserInfo> courseUserList = courseUserApi.findAllCourseUserByCourseId(courseId);
        //获取用户id数组
        List<Integer> userIds = new ArrayList<>();
        courseUserList.forEach(e -> userIds.add(e.getUserId()));
        //使用websocket给在线人提醒
        myWebSoket.sendMessageToCourseUser(message,userIds);
        //调用service层方法，数据库属于该课程的每一个人插入一条通知数据
        noticeService.insertAll(sendId,courseId,message);
    }


    /**
     * 给某个用户发通知
     * @param message
     */
    @PostMapping("/send/to/one")
    public void sendMessageToOne(Integer sendId, Integer acceptId, String message){
        log.info("[通知服务]sendId={},acceptId={} ,message={}",sendId,acceptId,message);
        //websocket推送
        myWebSoket.sendMessage(message,acceptId);
        //插入消息数据库
        noticeService.insert(messageFactory.getSimpleMessage(sendId,acceptId,message));
    }



}
