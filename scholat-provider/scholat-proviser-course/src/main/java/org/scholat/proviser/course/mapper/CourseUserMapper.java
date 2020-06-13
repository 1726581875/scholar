package org.scholat.proviser.course.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.scholat.proviser.course.dto.CourseDto;
import org.scholat.proviser.course.dto.CourseUserDto;
import org.scholat.proviser.course.entity.CourseUser;

import java.util.List;

@Mapper
public interface CourseUserMapper {

    //删除用户课程表记录
    public int deleteById(int userId ,int courseId);
    //插入
    public int insert(CourseUser courseUser);
    //更新
    public int update(CourseUser courseUser);

    public List<CourseDto> findCourseByUserId(int userId);//查找某用户的所有课程

    //查看课程里的学生
    public List<CourseUserDto> findCourseUser(@Param("courseId") int courseId, @Param("className") String className);

    //查找某课程的所有班级
    public List<String> findCourseClass(int courseId);

}
