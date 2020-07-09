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

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private MyWebSoket myWebSoket;

    @Resource
    private CourseUserServiceApi courseUserApi;


    /**
     * 分页查找某个用户的所有消息
     * @param userId
     * @param page
     * @return
     */
    @GetMapping("/all")
    public Object findAllMsg(@RequestParam("userId") Integer userId,
                             @RequestParam(value="page",defaultValue = "1") Integer page){

        return ResultUtil.success(noticeService.findAllByAcceptId(userId,page));
    }

    /**
     * 检查是否有新消息
     * @param userId
     * @return 返回消息条数
     */
    @GetMapping("/check")
    public int checkMessageByUserId(Integer userId){
       return noticeService.findUnReadNoticeAmount(userId);
    }


    /**
     * 消息设置为已读
     * @param noticeId
     * @return
     */
    @GetMapping("/read/{noticeId}")
    public Object readMsgById(@PathVariable Integer noticeId){
        log.info("[通知服务] 阅读id = {} 的消息",noticeId);
        noticeService.readMsgById(noticeId);
        return ResultUtil.success();
    }

    /**
     * 分页查找通知根据 用户名 和 通知的flag(已读，未读)
     * @param userId
     * @param flag
     * @param page
     * @return
     */
    @GetMapping("/all/notice")
    public Object findNoticeByFlag(Integer userId,Integer flag, Integer page){
        return ResultUtil.success(noticeService.findNoticeByUserIdAndFlag(userId,flag,page));
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


    @PostMapping("/delete/some")
    public Object deleteBySelection(List<Integer> noticeIds){
        noticeService.deleteNotice(noticeIds);
        return ResultUtil.success();
    }



}
