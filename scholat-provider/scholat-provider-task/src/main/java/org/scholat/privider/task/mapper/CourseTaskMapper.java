package org.scholat.privider.task.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 16:11
 */
@Mapper
public interface CourseTask {
    @Select("select * from task")
    List<CourseTask> getCourseTaskAll();

    @Update("update task set task_title = #{courseTask.taskTile}, task_content = #{courseTask.taskContent}," +
            "annex_path = #{courseTask.annexPath}, end_time = #{courseTask.endTime} where course_id = #{courseTask.course_Id}")
    int updateCourseTask(@Param("courseTask") CourseTask courseTask);

    @Delete("delete from task where where course_id = #{course_Id}")
    int deleteCourseTask(@Param("course_Id") int courseId);
}
