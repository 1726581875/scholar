package cn.scholat.service;


import cn.scholat.service.hystrix.CourseHystrix;
import org.scholat.common.ResultMsg;
import org.scholat.common.pojo.CourseUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "SCHOLAT-COURSE",fallback = CourseHystrix.class)
public interface CourseUserServiceApi {

    @PutMapping(value = "/course-api/join")
    public ResultMsg<Object> joinCourse(@RequestBody CourseUserInfo courseUser);

    @GetMapping("/course-api/all/course/{courseId}")
    public List<CourseUserInfo> findAllCourseUserByCourseId(@PathVariable("courseId") Integer courseId);

}
