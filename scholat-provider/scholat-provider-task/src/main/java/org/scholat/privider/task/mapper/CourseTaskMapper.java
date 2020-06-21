package org.scholat.privider.task.mapper;

import org.apache.ibatis.annotations.*;
import org.scholat.common.pojo.CourseTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 16:11
 */
@Mapper
@Repository
public interface CourseTaskMapper {

    @Select("SELECT  distinct cu.class_name, task_id, c.course_id, t.task_title, t.task_content, t.annex_path, t.end_time, t.create_time, u.user_id, u.user_name, c.course_name" +
            " FROM task t,course c,course_user cu,user_detail u  " +
            "where t.course_id = c.course_id and c.user_id = u.user_id and cu.course_id = c.course_id and t.course_id=#{courseId};")
    List<CourseTask> getCourseTaskByCourseId(@Param("courseId") int courseId);

    //查看作业要求
    @Select("select task_id, course_id, task_title, task_content, end_time, is_delay_submit from task " +
            "where course_id = #{courseId} and task_id = #{taskId}")
    CourseTask queryCourseTaskByCourseIdAndTaskId (@Param("courseId") int courseId, @Param("taskId") int taskId);

    //发布作业
    @Insert("insert into task(task_title, task_content, annex_path, end_time, is_delay_submit, ) " +
            "values(#{courseTask.taskTile}, #{courseTask.taskContent}," +
            "#{courseTask.annexPath}, #{courseTask.endTime}, #{courseTask.isDelaySubmit})")
    int addCourseTask(@Param("courseTask") CourseTask courseTask);

    //修改作业要求
    @Update("update task set task_title = #{courseTask.taskTitle}, task_content = #{courseTask.taskContent}," +
           "annex_path = #{courseTask.annexPath}, end_time = #{courseTask.endTime}, is_delay_submit = #{courseTask.isDelaySubmit}" +
            "where course_id = #{courseTask.courseId} and task_id = #{courseTask.taskId}")
    int updateCourseTask(@Param("courseTask") CourseTask courseTask);

    //删除某个作业
    @Delete("delete from task where where course_id = #{courseId} and task_id = #{taskId}")
    int deleteCourseTask(@Param("courseId") int courseId, @Param("taskId") int taskId);

    //查询该课程下的班级
    @Select("select distinct cu.class_name from course_user cu, task t " +
            "where cu.course_id = t.course_id and t.course_id = #{couserId}")
    List<String> queryClassName(@Param("couserId") int couserId);
}
