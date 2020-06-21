package org.scholat.privider.task.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.scholat.common.pojo.CourseTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 16:11
 */
@Mapper
@Repository
public interface HomeWorkMapper {

    @Select("select * from task")
    public List<CourseTask> getAllHomeWork ();

    @Select("SELECT  distinct task_id, c.course_id, t.task_title, t.task_content, t.annex_path, t.end_time, t.create_time, u.user_id, u.user_name, c.course_name\n" +
            " FROM task t,course c,user_detail u\n" +
            "where t.course_id = c.course_id and c.user_id=u.user_id and t.course_id=#{courseId};")
    public List<CourseTask> getHomeWorkByCourseId (@Param("courseId") int courseId);

    @Update("update homework set work_path = #{workPath} where work_id = #{workId}")
    public int updateHomeWork (@Param("workId") int workId);


    @Select("select course_name from course where course_id = #{courseId}")
    public String queryCourseName(@Param("courseId") int courseId);
}
