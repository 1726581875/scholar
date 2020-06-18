package ontice.controller;

import cn.scholat.service.CourseUserServiceApi;
import lombok.extern.slf4j.Slf4j;
import ontice.model.SelectionNotice;
import ontice.service.MyWebSoket;
import ontice.service.NoticeService;
import org.scholat.common.ResultMsg;
import org.scholat.common.pojo.CourseUserInfo;
import org.scholat.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
public class NoticeController {

    @Autowired
    private MyWebSoket webSoket;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private MyWebSoket myWebSoket;

    @Autowired
    private CourseUserServiceApi courseUserApi;

    @GetMapping("/msg")
    private Object getMsg(){
     return new SelectionNotice(1,"申请加入课程","ok","no");
    }


    @GetMapping("/all")
    public Object findAllMsg(@RequestParam("userId") Integer userId, @RequestParam(value="page",defaultValue = "1") Integer page){
        myWebSoket.sendMessage("11111111");
        return ResultUtil.success(noticeService.findAllByAcceptId(userId,page));
    }


    @GetMapping("/addCourse")
    public Object addCourse(CourseUserInfo courseUser,int noticeId){
        log.info("[通知服务] =======>{}noticeId={}",courseUser,noticeId);
       ResultMsg<Object> result = courseUserApi.joinCourse(courseUser);
        if(result.isSuccess()){
            noticeService.setNoticeResult(noticeId);
        }
        return result;
    }


    @PostMapping("/send/to/all")
    public void sendMessagetoAll(String message){
        webSoket.sendMessage(message);
    }



}
