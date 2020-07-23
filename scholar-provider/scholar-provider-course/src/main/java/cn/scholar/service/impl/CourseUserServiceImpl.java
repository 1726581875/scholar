package cn.scholar.service.impl;

import cn.scholar.dto.CourseDto;
import cn.scholar.entity.CourseUser;
import cn.scholar.mapper.CourseUserMapper;
import cn.scholar.dto.CourseUserDto;
import cn.scholar.service.CourseUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseUserServiceImpl implements CourseUserService {

    @Resource
    private CourseUserMapper courseUserMapper;


    @Override
    public int outCourse(int userId, int courseId) {
        return courseUserMapper.deleteById(userId,courseId);
    }

    @Override
    public int joinCourse(CourseUser courseUser) {
        return courseUserMapper.insert(courseUser);
    }

    @Override
    public int updateMsg(CourseUser courseUser) {
        return courseUserMapper.update(courseUser);
    }

    @Override
    public List<CourseDto> findUserjoinCourses(int userId) {

        return courseUserMapper.findCourseByUserId(userId);
    }

    @Override
    public List<CourseUserDto> findCourseUser(int courseId, String className) {
        return courseUserMapper.findCourseUser(courseId,className);
    }

    @Override
    public List<String> findCourseClass(int courseId) {
        return courseUserMapper.findCourseClass(courseId);
    }

    @Override
    public int deleteUserByCourseId(int courseId) {

        return courseUserMapper.deleteUserByCourseId(courseId);
    }

    @Override
    public List<CourseUser> findAllCourseUserByCourseId(int courseId) {

        return courseUserMapper.findAllCourseUserByCourseId(courseId);
    }
}
