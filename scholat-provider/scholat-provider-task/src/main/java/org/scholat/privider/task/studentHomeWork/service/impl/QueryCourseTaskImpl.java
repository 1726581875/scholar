package org.scholat.privider.task.studentHomeWork.service.impl;

import org.scholat.common.pojo.CourseTask;
import org.scholat.privider.task.mapper.CourseTaskMapper;
import org.scholat.privider.task.studentHomeWork.service.IQueryCourseTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yrk
 * @date 2020/6/17 - 16:33
 */
@Service
public class QueryCourseTaskImpl implements IQueryCourseTaskService {

    @Autowired
    private CourseTaskMapper courseTaskMapper;
    //查看作业要求
    @Override
    public CourseTask queryCourseTaskByCourseIdAndTaskId(int courseId, int taskId) {
        return courseTaskMapper.queryCourseTaskByCourseIdAndTaskId(courseId, taskId);
    }
}
