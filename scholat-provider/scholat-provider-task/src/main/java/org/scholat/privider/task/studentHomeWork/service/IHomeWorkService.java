package org.scholat.privider.task.studentHomeWork.service;

import org.scholat.common.pojo.HomeWork;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 17:12
 */
public interface IHomeWork {

    List<HomeWork> getHomeWorkAll ();

    int updateHomeWork (HomeWork homeWork);
}
