package org.scholat.provider.task.api.studentTask;

import org.scholat.common.pojo.HomeWork;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 10:24
 */

public interface HomeWorkApi {

  @PostMapping("/homeWork/list")
  List<HomeWork> getHomeWorkAll ();

  @GetMapping("/homeWork/update")
  int updateHomeWork (int workId);

}
