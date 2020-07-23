package cn.scholar.taskTeacher.service.api;

import cn.scholar.common.pojo.CourseTask;
import cn.scholar.common.pojo.HomeWork;
import cn.scholar.taskTeacher.service.api.hystrix.CourseTaskHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 10:24
 */
@FeignClient(value = "SCHOLAT-COURSETASK",fallbackFactory = CourseTaskHystrix.class)
public interface CourseTaskApi {
    /**
     * 查找教师某课程下所有布置的作业（作业）
     * @return
     */
    @PostMapping("/CourseTask/list/{courseId}")
    List<HomeWork> getCourseTaskByCourseId (int courseId);

    @PutMapping("/CourseTask/update/{courseTask}")
    int updateCourseTask(@RequestParam("courseTask") CourseTask courseTask);

    @DeleteMapping("/CourseTask/delete/{courseId}")
    int deleteCourseTask(@RequestParam("courseId") int courseId);
}
