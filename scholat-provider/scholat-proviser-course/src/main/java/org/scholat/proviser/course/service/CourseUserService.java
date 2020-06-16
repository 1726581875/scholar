package org.scholat.proviser.course.service;

import org.scholat.proviser.course.dto.CourseDto;
import org.scholat.proviser.course.dto.CourseUserDto;
import org.scholat.proviser.course.entity.CourseUser;

import java.util.List;

public interface CourseUserService {

    //退出课程
    public int outCourse(int userId ,int courseId);
    //加入课程
    public int joinCourse(CourseUser courseUser);
    //更新信息
    public int updateMsg(CourseUser courseUser);

    public List<CourseDto> findUserjoinCourses(int userId);

    public List<CourseUserDto> findCourseUser(int courseId,String className);

    public List<String> findCourseClass(int courseId);

    public int deleteUserByCourseId(int courseId);
}
