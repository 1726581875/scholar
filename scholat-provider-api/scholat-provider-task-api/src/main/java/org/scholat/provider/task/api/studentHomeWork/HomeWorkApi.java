package org.scholat.provider.task.api.studentHomeWork;

import org.scholat.common.pojo.HomeWork;
import org.scholat.provider.task.api.studentHomeWork.hystrix.HomeWorkHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 10:24
 */
@FeignClient(value = "SCHOLAT-HOMEWORK", fallbackFactory = HomeWorkHystrix.class)
public interface HomeWorkApi {
  /**
   * 查询用户的某一课程下的所有作业
   * @param courseId
   * @return
   */
  @PostMapping("/homeWork/list/{courseId}")
  List<HomeWork> getHomeWorkByCourseId (int courseId);

  /**
   * 查询用户的所有作业任务
   * @return
   */
  @PostMapping("/homeWork/list")
  List<HomeWork> getAllHomeWork ();

  /**
   * 学生修改上传的作业，就更新homework表中的作业存储路径work_path
   * @param workId
   * @return
   */
  @PutMapping("/homeWork/update/{workId}")
  int updateHomeWork (@RequestParam("workId") int workId);

}
