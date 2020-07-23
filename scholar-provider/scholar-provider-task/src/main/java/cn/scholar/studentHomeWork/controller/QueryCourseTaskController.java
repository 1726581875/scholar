package cn.scholar.studentHomeWork.controller;

import cn.scholar.common.annotation.LogFilter;
import cn.scholar.common.pojo.CourseTask;
import cn.scholar.common.utils.ResultUtil;
import cn.scholar.studentHomeWork.service.IQueryCourseTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yrk
 * @date 2020/6/17 - 16:36
 */
@RestController
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true") //允许跨域请求
public class QueryCourseTaskController {

    @Autowired
    private IQueryCourseTaskService queryCourseTask;

    /**
     * 查看作业要求
     * @param courseId
     * @param taskId
     * @return
     */
    @GetMapping("/queryCourseTask")
    @LogFilter("保存请求日志")
    public Object queryCourseTaskByCourseIdAndTaskId (@RequestParam("courseId") int courseId, @RequestParam("taskId") int taskId){
        CourseTask courseTask = queryCourseTask.queryCourseTaskByCourseIdAndTaskId(courseId, taskId);
        if (courseTask != null){
            return ResultUtil.success(courseTask);
        }else {
            return ResultUtil.success("查看作业要求失败");
        }
    }

}
