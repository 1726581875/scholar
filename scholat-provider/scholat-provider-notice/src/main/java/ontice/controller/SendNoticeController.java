package ontice.controller;

import cn.scholat.service.CourseUserServiceApi;
import lombok.extern.slf4j.Slf4j;
import ontice.service.MyWebSoket;
import ontice.service.NoticeService;
import org.scholat.common.pojo.CourseUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class SendNoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private MyWebSoket myWebSoket;

    @Autowired
    private CourseUserServiceApi courseUserApi;

    /**
     * 给全站在线用户发通知
     * @param message
     */
    @PostMapping("/send/to/all")
    public void sendMessageToAll(String message){
        log.info("[通知服务]要发送的message======>{}",message);
        myWebSoket.sendMessage(message);
    }

    /**
     * 对某个班级在线人发提示
     */
    @PostMapping("/send/to/course/user")
    public void sendMessageToCourseUser(Integer courseId,String message){
        log.info("[通知服务] sendMessage======>{}",message);
        List<CourseUserInfo> courseUserList = courseUserApi.findAllCourseUserByCourseId(courseId);
        List<Integer> userIds = new ArrayList<>();
        courseUserList.forEach(e -> userIds.add(e.getUserId()));
        myWebSoket.sendMessageToCourseUser(message,userIds);
    }


    /**
     * 给全站在线用户发通知
     * @param message
     */
    @PostMapping("/send/to/one")
    public void sendMessageToOne(Integer acceptId, String message){
        log.info("[通知服务]要发送的给userId={} ,message={}",acceptId,message);
        myWebSoket.sendMessage(message,acceptId);
    }


}
