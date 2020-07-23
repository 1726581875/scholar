package cn.scholar.studentHomeWork.controller;

import cn.scholar.common.pojo.HomeWork;
import cn.scholar.studentHomeWork.service.IHomeWorkService;
import cn.scholar.vo.HomeWorkVo;
import lombok.extern.slf4j.Slf4j;
import cn.scholar.common.utils.CookieUtil;
import cn.scholar.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 17:13
 */
@RestController
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true") //允许跨域请求 (allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("/homeWork")
@Slf4j
public class HomeWorkController {

    @Autowired
   private IHomeWorkService iHomeWorkService;
    /**
     * 查询用户的某一课程下的所有作业
     * @param courseId
     * @return
     */
    @PostMapping("/list/{courseId}")
    public Object getHomeWorkByCourseId (@PathVariable("courseId") int courseId, HttpServletRequest request){

        Cookie cookie = CookieUtil.getCookie(request,"userId");
        Integer userId = Integer.parseInt(cookie.getValue());
        log.info("[task微服务] =======》 userId={}",userId);
        System.err.println("HomeWorkController => courseId:"+courseId);
        HomeWorkVo homeWorkVo = iHomeWorkService.getHomeWorkByCourseId(courseId);
        homeWorkVo.setUserName(iHomeWorkService.findUserNameByUserId(userId));
        return  ResultUtil.success(homeWorkVo);
    }

    /**
     * 查询用户的所有作业任务
     * @return
     */
    @PostMapping("/list")
    public List<HomeWork> getAllHomeWork (){
        return null;
    }

    /**
     * 学生修改上传的作业，就更新homework表中的作业存储路径work_path
     * @param workId
     * @return
     */
    @PutMapping("/update/workId}")
    public Object updateHomeWork (@PathVariable("workId") int workId){
        int i = iHomeWorkService.updateHomeWork(workId);
        if (i > 0){
           return ResultUtil.success(i);
        }else {
            return ResultUtil.fail("修改作业失败！");
        }
    }

    @GetMapping("/get/user/name/{userId}")
    public  Object findUserName(@PathVariable Integer userId){
        return ResultUtil.success(iHomeWorkService.findUserNameByUserId(userId));
    }

    @GetMapping("/get/course/name/{courseId}")
    public  Object findCourseName(@PathVariable Integer courseId){
        return ResultUtil.success(iHomeWorkService.findCourseNameByCourseId(courseId));
    }

}
