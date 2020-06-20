package org.scholat.provider.task.api.studentHomeWork.hystrix;

import feign.hystrix.FallbackFactory;
import org.scholat.common.pojo.HomeWork;
import org.scholat.provider.task.api.studentHomeWork.HomeWorkApi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 15:29
 * 学生作业服务降级
 */
@Component
public class HomeWorkHystrix implements FallbackFactory<HomeWorkApi> {
    @Override
    public HomeWorkApi create(Throwable throwable) {
        return new HomeWorkApi() {

            @Override
            public List<HomeWork> getHomeWorkByCourseId(int courseId) {
                return null;
            }

            @Override
            public List<HomeWork> getAllHomeWork() {
                return null;
            }

            @Override
            public int updateHomeWork(int workId) {
                return 0;
            }

        };
    }
}
