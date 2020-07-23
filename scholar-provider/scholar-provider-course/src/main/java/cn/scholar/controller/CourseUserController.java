package cn.scholar.controller;


import cn.scholar.entity.CourseUser;
import lombok.extern.slf4j.Slf4j;
import cn.scholar.common.RespBean;
import cn.scholar.common.message.enums.CommonEnum;
import cn.scholar.common.pojo.CourseUserInfo;
import cn.scholar.service.CourseUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-api")
@Slf4j
public class CourseUserController {

    @Autowired
    private CourseUserService courseUserService;

    @PutMapping(value = "/join")
    public RespBean<Object> joinCourse(@RequestBody CourseUserInfo courseUser){
        log.info("course服务，接收参数CourseUser======>{}",courseUser);

        CourseUser course = new CourseUser();
        BeanUtils.copyProperties(courseUser,course);
        int m = courseUserService.joinCourse(course);
        if(m==1){
            return new RespBean<>(CommonEnum.SUCCESS,null);
        }
        return new RespBean<>(CommonEnum.UNKONW_ERROR,null);
    }


    @GetMapping("/all/course/{courseId}")
    public List<CourseUser> findAllCourseUserByCourseId(@PathVariable Integer courseId){
        log.info("[课程微服务] findAllCourseUserByCourseId  ====>courseId={}",courseId);
      return  courseUserService.findAllCourseUserByCourseId(courseId);
    }




}


