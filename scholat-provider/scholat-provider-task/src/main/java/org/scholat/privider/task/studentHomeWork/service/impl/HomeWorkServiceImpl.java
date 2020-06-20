package org.scholat.privider.task.studentHomeWork.service.impl;

import org.scholat.common.pojo.HomeWork;
import org.scholat.privider.task.mapper.HomeWorkMapper;
import org.scholat.privider.task.studentHomeWork.service.IHomeWork;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 17:13
 */
public class HomeWorkImpl implements IHomeWork {
    @Autowired
    HomeWorkMapper homeWorkMapper;
    @Override
    public List<HomeWork> getHomeWorkAll() {
        return homeWorkMapper.getHomeWorkAll();
    }

    @Override
    public int updateHomeWork(HomeWork homeWork) {
        return homeWorkMapper.updateHomeWork(homeWork);
    }
}
