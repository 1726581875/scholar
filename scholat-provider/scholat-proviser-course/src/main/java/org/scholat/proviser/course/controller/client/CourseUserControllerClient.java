package org.scholat.proviser.course.controller.client;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.scholat.common.ResultMsg;
import org.scholat.common.constant.MyConstant;
import org.scholat.common.utils.ResultUtil;
import org.scholat.proviser.course.dto.CourseDto;
import org.scholat.proviser.course.entity.CourseUser;
import org.scholat.proviser.course.service.CourseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CourseUserControllerClient {

    @Autowired
    private CourseUserService courseUserService;

    @PostMapping("/join")
    public Object joinCourse(CourseUser courseUser){
        int m = courseUserService.joinCourse(courseUser);
        return (m==1) ? ResultUtil.success() : ResultUtil.fail("加入课程失败");
    }

    @PostMapping("/out")
   public Object outCurse(Integer userId , Integer courseId){
       int m = courseUserService.outCourse(userId, courseId);
       return (m==1) ? ResultUtil.success() : ResultUtil.fail("退出课程失败");
   }

    /**
     * 查找该用户加入的课程
     * @param userId
     * @param page
     * @return
     */
    @GetMapping("/{userId}/join/{page}")
   public Object findMyJoInCourse(@PathVariable  Integer userId, @PathVariable Integer page){
        List<CourseDto> courses = courseUserService.findUserjoinCourses(userId);
        //使用分页插件分页，设置页面大小和第几页
        PageHelper.startPage(page, MyConstant.PAGE_SIZE);
        PageInfo<CourseDto> pageInfo = new PageInfo<CourseDto>(courses);
        return ResultUtil.success(pageInfo);
   }

    /**
     * 查询该课程的用户列表，按班级分
     * @param courseId
     * @param className
     * @return
     */
   @GetMapping("/{courseId}/class/name")
   public Object findCourseUser(@PathVariable  Integer courseId ,@RequestParam String className){

        return ResultUtil.success(courseUserService.findCourseUser(courseId,className));
    }

    @GetMapping("/{courseId}/class")
    public Object findCourseClass(@PathVariable int courseId){
       return ResultUtil.success(courseUserService.findCourseClass(courseId));
    }


}
