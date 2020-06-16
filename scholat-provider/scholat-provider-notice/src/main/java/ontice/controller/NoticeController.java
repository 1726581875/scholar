package ontice.controller;

import cn.scholat.service.CourseUserServiceApi;
import lombok.extern.slf4j.Slf4j;
import ontice.model.SelectionNotice;
import ontice.service.NoticeService;
import org.scholat.common.ResultMsg;
import org.scholat.common.pojo.CourseUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@CrossOrigin
@Slf4j
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CourseUserServiceApi courseUserApi;

    @GetMapping("/msg")
    private Object getMsg(){
     return new SelectionNotice(1,"申请加入课程","ok","no");
    }


    @GetMapping("/all")
    public Object findAllMsg(@RequestParam("userId") Integer userId, @RequestParam(value="page",defaultValue = "1") Integer page){

        return noticeService.findAllByAcceptId(userId,page);
    }


    @GetMapping("/addCourse")
    @Transactional
    public Object addCourse(CourseUserInfo courseUser,int noticeId){
        log.info("[通知服务] =======>{}noticeId={}",courseUser,noticeId);
       ResultMsg<Object> result = courseUserApi.joinCourse(courseUser);
        if(result.isSuccess()){
            noticeService.setNoticeResult(noticeId);
        }
        return result;
    }



}
