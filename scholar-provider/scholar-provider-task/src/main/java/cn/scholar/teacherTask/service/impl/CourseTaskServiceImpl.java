package cn.scholar.teacherTask.service.impl;

import cn.scholar.common.pojo.CourseTask;
import cn.scholar.mapper.CourseTaskMapper;
import cn.scholar.teacherTask.service.ICourseTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 17:19
 */
@Service
public class CourseTaskServiceImpl implements ICourseTaskService {

    @Autowired
    CourseTaskMapper courseTaskMapper;

    /**
     * 查找教师某课程下所有布置的作业（作业）
     * @return
     */
    @Override
    public List<CourseTask> getCourseTaskByCourseId(int courseId) {
        return courseTaskMapper.getCourseTaskByCourseId(courseId);
    }

    @Override
    public int addCourseTask(CourseTask courseTask) {
        return courseTaskMapper.addCourseTask(courseTask);
    }

    @Override
    public int updateTaskDemand(CourseTask courseTask) {
        return courseTaskMapper.updateCourseTask(courseTask);
    }


    @Override
    public int deleteCourseTask(int courseId, int taskId) {
        return courseTaskMapper.deleteCourseTask(courseId, taskId);
    }

    @Override
    public List<String> queryClassName(int couserId) {
        return courseTaskMapper.queryClassName(couserId);
    }

    @Override
    public CourseTask queryCourseTaskByCourseIdAndTaskId(int courseId, int taskId) {
        return courseTaskMapper.queryCourseTaskByCourseIdAndTaskId(courseId, taskId);
    }
}
