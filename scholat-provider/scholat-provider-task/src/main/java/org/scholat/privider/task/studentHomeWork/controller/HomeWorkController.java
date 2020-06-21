package org.scholat.privider.task.studentHomeWork.controller;

import org.scholat.common.pojo.HomeWork;
import org.scholat.common.utils.ResultUtil;
import org.scholat.privider.task.studentHomeWork.service.IHomeWorkService;
import org.scholat.privider.task.vo.HomeWorkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yrk
 * @date 2020/6/12 - 17:13
 */
@RestController
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true") //允许跨域请求 (allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("/homeWork")
public class HomeWorkController {

    @Autowired
   private IHomeWorkService iHomeWorkService;
    /**
     * 查询用户的某一课程下的所有作业
     * @param courseId
     * @return
     */
    @PostMapping("/list/{courseId}")
    public Object getHomeWorkByCourseId (@PathVariable("courseId") int courseId){
        System.err.println("HomeWorkController => courseId:"+courseId);
        HomeWorkVo homeWorkVo = iHomeWorkService.getHomeWorkByCourseId(courseId);
        return  ResultUtil.success(homeWorkVo);
    }

    /**
     * 查询用户的所有作业任务
     * @return
     */
    @PostMapping("/list")
    public List<HomeWork> getAllHomeWork (){
        return null;
    }

    /**
     * 学生修改上传的作业，就更新homework表中的作业存储路径work_path
     * @param workId
     * @return
     */
    @PutMapping("/update/workId}")
    public Object updateHomeWork (@PathVariable("workId") int workId){
        int i = iHomeWorkService.updateHomeWork(workId);
        if (i > 0){
           return ResultUtil.success(i);
        }else {
            return ResultUtil.fail("修改作业失败！");
        }
    }
}
