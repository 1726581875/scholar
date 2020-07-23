package cn.scholar.question.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SCHOLAT-NOTICE")
public interface NoticeServiceApi {

    //给所有在线用户发消息的feign接口
    @PostMapping("/send/to/all")
    public void sendMessagetoAll(@RequestParam(value = "message") String message);

    //给某个课程的人发信息的接口
    @PostMapping("/send/to/course/user")
    public void sendMessageToCourseUser(@RequestParam("sendId") Integer sendId,
                                        @RequestParam("courseId") Integer courseId,
                                        @RequestParam("message") String message);


    //给某个人发消息
    @PostMapping("/send/to/one")
    public void sendMessageToOne(@RequestParam("sendId") Integer sendId,
                                        @RequestParam("acceptId") Integer acceptId,
                                        @RequestParam("message") String message);


}
