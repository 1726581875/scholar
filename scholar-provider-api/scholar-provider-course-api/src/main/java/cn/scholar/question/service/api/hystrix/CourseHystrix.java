package cn.scholar.question.service.api.hystrix;

import cn.scholar.course.service.api.CourseUserServiceApi;
import cn.scholar.common.RespBean;
import cn.scholar.common.pojo.CourseUserInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseHystrix implements CourseUserServiceApi {
    @Override
    public RespBean<Object> joinCourse(CourseUserInfo courseUser) {
        return new RespBean<>(-3,null,"课程微服务不行了");
    }

    @Override
    public List<CourseUserInfo> findAllCourseUserByCourseId(Integer courseId) {
        return null;
    }
}
