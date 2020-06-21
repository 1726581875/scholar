package org.scholat.provider.task.api.teacherTask.hystrix;

import feign.hystrix.FallbackFactory;
import org.scholat.common.pojo.CourseTask;
import org.scholat.common.pojo.HomeWork;
import org.scholat.provider.task.api.teacherTask.CourseTaskApi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 15:19
 * 教学布置的任务-服务降级
 */

@Component
public class CourseTaskHystrix implements FallbackFactory<CourseTaskApi> {
    @Override
    public CourseTaskApi create(Throwable throwable) {
        return new CourseTaskApi() {

            @Override
            public List<HomeWork> getCourseTaskByCourseId(int courseId) {
                return null;
            }

            @Override
            public int updateCourseTask(CourseTask courseTask) {
                return 0;
            }

            @Override
            public int deleteCourseTask(int courseId) {
                return 0;
            }
        };
    }
}
