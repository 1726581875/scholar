package cn.scholar.studentHomeWork.service.impl;

import cn.scholar.common.pojo.CourseTask;
import cn.scholar.studentHomeWork.service.IHomeWorkService;
import cn.scholar.mapper.HomeWorkMapper;
import cn.scholar.vo.HomeWorkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public HomeWorkVo getHomeWorkByCourseId(int courseId) {



        List<CourseTask> homeWorkByCourseId = homeWorkMapper.getHomeWorkByCourseId(courseId);
        List<CourseTask> taskList =  homeWorkMapper.getHomeWorkByCourseId(courseId);
        String courseName = homeWorkMapper.queryCourseName(courseId);
        System.out.println(courseName);
        HomeWorkVo homeWorkVo = new HomeWorkVo();
        homeWorkVo.setCourseName(courseName);

        homeWorkVo.setTaskList(taskList);
        return homeWorkVo;
    }

    @Override
    public List<CourseTask> getAllHomeWork() {
        return null;
    }

    @Override
    public int updateHomeWork(int workId) {
        return homeWorkMapper.updateHomeWork(workId);
    }

    @Override
    public String findUserNameByUserId(int userId) {
        return homeWorkMapper.findUserNameByUserId(userId);
    }

    @Override
    public String findCourseNameByCourseId(int courseId) {
        return homeWorkMapper.findCourseNameByCourseId(courseId);
    }
}
