package org.scholat.privider.task.teacherTask.controller;

import org.scholat.common.annotation.LogFilter;
import org.scholat.common.pojo.CourseTask;
import org.scholat.common.utils.ResultUtil;
import org.scholat.privider.task.teacherTask.service.ICourseTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/13 - 20:23
 */
@RestController
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true") //允许跨域请求
@RequestMapping("/courseTask")
public class CourseTaskController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CourseTaskController.class);
    @Autowired
    ICourseTaskService iCourseTaskService;

    /**
     * 查找教师某课程下所有布置的作业（作业）
     * @return
     */
    @PostMapping("/list/{courseId}")
    public Object getCourseTaskByCourseId(@PathVariable("courseId") int courseId) {

        LOGGER.info("查询教师某课程下布置的所有作业");
        List<CourseTask> courseTaskByCourseId = iCourseTaskService.getCourseTaskByCourseId(courseId);
        for (CourseTask courseTask : courseTaskByCourseId) {
            System.err.println("======> courseTask = " + courseTask.getClassName());
        }
        return ResultUtil.success(courseTaskByCourseId);
    }

    /**
     * 发布作业
     */
    @PostMapping("/addCourseTask")
    public Object addCourseTask(@RequestBody CourseTask courseTask){

        int i = iCourseTaskService.addCourseTask(courseTask);
        if (i > 0){
            return ResultUtil.success("作业发布成功！");
        }else {
            return ResultUtil.fail("作业发布失败！");
        }

    }


    /**
     *更新作业要求
     * @param courseTask
     * @return
     */
    @PostMapping("/updateTaskDemand")
    public Object updateTaskDemand(@RequestBody CourseTask courseTask){

        LOGGER.info("----------CourseTaskController中的updateTaskDemand()方法执行了----------");
        int i = iCourseTaskService.updateTaskDemand(courseTask);
        if (i > 0){
            return ResultUtil.success("修改成功！");
        }else {
            return ResultUtil.fail("修改失败！");
        }

    }

    /**
     * 删除发布的作业
     * @param courseId
     * @param taskId
     * @return
     */
    @PostMapping("/deleteCourseTask")
    public Object deleteCourseTask(@RequestParam("courseId") int courseId, @RequestParam("taskId") int taskId){

        int i = iCourseTaskService.deleteCourseTask(courseId, taskId);
        if (i > 0){
            return ResultUtil.success("修改成功！");
        }else {
            return ResultUtil.fail("修改失败！");
        }

    }

    /**
     * 回显作业要求
     * @param courseId
     * @param taskId
     * @return
     */
    @GetMapping("/echoCourseTask")
    @LogFilter("保存请求日志")
    public Object queryCourseTaskByCourseIdAndTaskId (@RequestParam("courseId") int courseId, @RequestParam("taskId") int taskId){
        CourseTask courseTask = iCourseTaskService.queryCourseTaskByCourseIdAndTaskId(courseId, taskId);
        List<String> classNames = iCourseTaskService.queryClassName(courseId);

        courseTask.setClassName(classNames); //这个课程下对应的所有班级

        if (courseTask != null){
            return ResultUtil.success(courseTask);
        }else {
            return ResultUtil.success("查看作业要求失败");
        }
    }

}
