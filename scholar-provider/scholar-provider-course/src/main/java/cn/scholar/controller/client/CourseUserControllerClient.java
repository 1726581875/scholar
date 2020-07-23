package cn.scholar.controller.client;


import cn.scholar.entity.CourseUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import cn.scholar.common.constant.MyConstant;
import cn.scholar.common.utils.ResultUtil;
import cn.scholar.dto.CourseDto;
import cn.scholar.service.CourseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class CourseUserControllerClient {

    @Autowired
    private CourseUserService courseUserService;

    @GetMapping("/join")
    public Object joinaCourse(CourseUser courseUser){
        int m = courseUserService.joinCourse(courseUser);
        return (m==1) ? ResultUtil.success() : ResultUtil.fail("加入课程失败");
    }

    @PostMapping(value = "/out")
   public Object outCurse(Integer userId , Integer courseId){
        log.info("userId={} , courseId={}",userId,courseId);
       int m = courseUserService.outCourse(userId, courseId);
       return (m==1) ? ResultUtil.success() : ResultUtil.fail("退出课程失败");
   }

    /**
     * 查找该用户加入的课程
     * @param userId
     * @param page
     * @return
     */
    @GetMapping("/user/{userId}/all/join")
   public Object findMyJoInCourse(@PathVariable  Integer userId,Integer page){
        //使用分页插件分页，设置页面大小和第几页
        PageHelper.startPage(page, MyConstant.PAGE_SIZE);
        List<CourseDto> courses = courseUserService.findUserjoinCourses(userId);
        PageInfo<CourseDto> pageInfo = new PageInfo<CourseDto>(courses);
        return ResultUtil.success(pageInfo);
   }

    /**
     * 查询该课程的用户列表，按班级分
     * @param courseId
     * @param className
     * @return
     */
   @GetMapping("/{courseId}/class/user")
   public Object findCourseUser(@PathVariable  Integer courseId ,@RequestParam(value = "className" ,required = false) String className){

        return ResultUtil.success(courseUserService.findCourseUser(courseId,className));
    }

    @GetMapping("/{courseId}/class")
    public Object findCourseClass(@PathVariable int courseId){
       return ResultUtil.success(courseUserService.findCourseClass(courseId));
    }


}
