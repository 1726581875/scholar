package cn.scholat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SCHOLAT-NOTICE")
public interface NoticeServiceApi {

    //给所有在线用户发消息的feign接口
    @PostMapping("/send/to/all")
    public void sendMessagetoAll(@RequestParam(value = "message") String message);

    //给某个课程在线人推送信息的接口
    @PostMapping("/send/to/course/user")
    public void sendMessageToCourseUser(@RequestParam("courseId") Integer courseId,
                                        @RequestParam("message") String message);

   //发布作业，批量插入信息表
    @PostMapping("/insert/homework/msg")
    public void insertAllCourse(@RequestParam(value = "sendId") Integer sendId ,
                                @RequestParam(value = "courseId") Integer courseId);


    @PostMapping("/send/to/one")
    public void sendMessageToOne(@RequestParam(value = "acceptId") Integer acceptId,
                                 @RequestParam(value = "message") String message);
}
