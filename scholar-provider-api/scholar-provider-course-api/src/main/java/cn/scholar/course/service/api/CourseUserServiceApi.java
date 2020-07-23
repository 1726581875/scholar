package cn.scholar.course.service.api;


import cn.scholar.question.service.api.hystrix.CourseHystrix;
import cn.scholar.common.RespBean;
import cn.scholar.common.pojo.CourseUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "SCHOLAT-COURSE",fallback = CourseHystrix.class)
public interface CourseUserServiceApi {

    @PutMapping(value = "/course-api/join")
    public RespBean<Object> joinCourse(@RequestBody CourseUserInfo courseUser);

    @GetMapping("/course-api/all/course/{courseId}")
    public List<CourseUserInfo> findAllCourseUserByCourseId(@PathVariable("courseId") Integer courseId);

}
