package org.scholat.privider.task.studentHomeWork.service.impl;

import org.scholat.common.pojo.CourseTask;
import org.scholat.common.pojo.HomeWork;
import org.scholat.privider.task.mapper.HomeWorkMapper;
import org.scholat.privider.task.studentHomeWork.service.IHomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 17:13
 */
@Service
public class HomeWorkServiceImpl implements IHomeWorkService {
    @Autowired
    HomeWorkMapper homeWorkMapper;


    @Override
    public List<CourseTask> getHomeWorkByCourseId(int courseId) {

        List<CourseTask> homeWorkByCourseId = homeWorkMapper.getHomeWorkByCourseId(courseId);
        return homeWorkMapper.getHomeWorkByCourseId(courseId);
    }

    @Override
    public List<CourseTask> getAllHomeWork() {
        return null;
    }

    @Override
    public int updateHomeWork(int workId) {
        return homeWorkMapper.updateHomeWork(workId);
    }
}
