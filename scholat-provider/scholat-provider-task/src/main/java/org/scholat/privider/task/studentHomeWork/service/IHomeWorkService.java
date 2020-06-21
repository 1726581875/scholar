package org.scholat.privider.task.studentHomeWork.service;

import org.scholat.common.pojo.CourseTask;
import org.scholat.privider.task.vo.HomeWorkVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 17:12
 */
public interface IHomeWorkService {

    /**
     * 查询用户的某一课程下的所有作业
     * @param courseId
     * @return
     */

    HomeWorkVo getHomeWorkByCourseId (int courseId);

    /**
     * 查询用户的所有作业任务
     * @return
     */

    List<CourseTask> getAllHomeWork ();

    /**
     * 学生修改上传的作业，就更新homework表中的作业存储路径work_path
     * @param workId
     * @return
     */

    int updateHomeWork (@RequestParam("workId") int workId);
}
