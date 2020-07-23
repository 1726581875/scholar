package cn.scholar.teacherTask.service;

import cn.scholar.common.pojo.CourseTask;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 17:17
 */
public interface ICourseTaskService {

    /**
     * 查找教师某课程下所有布置的作业（作业）
     * @return
     */
    List<CourseTask> getCourseTaskByCourseId (int courseId);

    /**
     * 发布作业
     * @param courseTask
     * @return
     */
    int addCourseTask(CourseTask courseTask);

    /**
     *更新作业要求
     * @param courseTask
     * @return
     */
    int updateTaskDemand(CourseTask courseTask);

    /**
     * 删除某个作业
     * @param courseId
     * @param taskId
     * @return
     */
    int deleteCourseTask(int courseId, int taskId);

    /**
     * 查询该课程下的班级
     * @param couserId
     * @return
     */
    List<String> queryClassName(int couserId);

    //查询、回显作业要求
    CourseTask queryCourseTaskByCourseIdAndTaskId (int courseId, int taskId);
}
