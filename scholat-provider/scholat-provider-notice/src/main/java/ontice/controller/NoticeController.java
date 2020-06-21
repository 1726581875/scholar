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
    private NoticeService noticeService;

    @Autowired
    private MyWebSoket myWebSoket;

    @Autowired
    private CourseUserServiceApi courseUserApi;


    @GetMapping("/msg")
    private Object getMsg(){
     return new SelectionNotice(1,"申请加入课程","ok","no");
    }

    /**
     * 查找某个用户的所有消息
     * @param userId
     * @param page
     * @return
     */
    @GetMapping("/all")
    public Object findAllMsg(@RequestParam("userId") Integer userId, @RequestParam(value="page",defaultValue = "1") Integer page){

        return ResultUtil.success(noticeService.findAllByAcceptId(userId,page));
    }

    /**
     * 同意申请课程url，如果点击同意，插入课程
     * @param courseUser
     * @param noticeId
     * @return
     */
    @GetMapping("/addCourse")
    public Object addCourse(CourseUserInfo courseUser,int noticeId){
        log.info("[通知服务] =======>{}noticeId={}",courseUser,noticeId);
       ResultMsg<Object> result = courseUserApi.joinCourse(courseUser);
        if(result.isSuccess()){
            noticeService.setNoticeResult(noticeId);
        }
        return result;
    }

    /**
     * 给全站在线用户发通知
     * @param message
     */
//    @PostMapping("/send/to/all")
//    public void sendMessageToAll(String message){
//        log.info("[通知服务]要发送的message======>{}",message);
//        myWebSoket.sendMessage(message);
//    }

    /**
     * 对某个班级在线人发提示
     */
//    @PostMapping("/send/to/course/user")
//    public void sendMessageToCourseUser(Integer sendId , Integer courseId,String message){
//        log.info("[通知服务] sendMessage======>{}",message);
//        List<CourseUserInfo> courseUserList = courseUserApi.findAllCourseUserByCourseId(courseId);
//        List<Integer> userIds = new ArrayList<>();
//        courseUserList.forEach(e -> userIds.add(e.getUserId()));
//        myWebSoket.sendMessageToCourseUser(message,userIds);
//    }


    /**
     * 老师发布作业时，可以调用，给每个课程的用户插入一条消息
      * @param sendId
     * @param courseId
     */
    @PostMapping("/insert/homework/msg")
    public void insertAllCourse(Integer sendId , Integer courseId){
        noticeService.insertAll(sendId, courseId);
    }

    /**
     * 检查是否有新消息
     * @param userId
     * @return 返回消息条数
     */
    @GetMapping("/check")
    public int checkMessageByUserId(Integer userId){
       return noticeService.findNoticeAmountByUserId(userId);
    }

    /**
     * 删除某条消息
     * @param noticeId
     * @return
     */
    @GetMapping("/delete/{noticeId}")
    public Object deleteMsgById(@PathVariable Integer noticeId){
        log.info("[通知服务] 删除id = {} 的消息",noticeId);
        noticeService.deleteNotice(noticeId);
        return ResultUtil.success();
    }

    /**
     * 设置为已读
     * @param noticeId
     * @return
     */
    @GetMapping("/read/{noticeId}")
    public Object readMsgById(@PathVariable Integer noticeId){
        log.info("[通知服务] 阅读id = {} 的消息",noticeId);
        noticeService.readMsgById(noticeId);
        return ResultUtil.success();
    }

    @GetMapping("/all/notice")
    public Object findNoticeByFlag(Integer userId,Integer flag, Integer page){

        return ResultUtil.success(noticeService.findNoticeByCondition(userId,flag,page));
    }


}
