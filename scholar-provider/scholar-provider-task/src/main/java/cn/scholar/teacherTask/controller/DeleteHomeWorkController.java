package cn.scholar.teacherTask.controller;

import cn.scholar.common.aliyunOSS.OSSUtil;
import com.aliyun.oss.OSSClient;
import cn.scholar.common.aliyunOSS.OSSClientConstants;
import cn.scholar.common.utils.ResultUtil;
import cn.scholar.teacherTask.service.IDeleteHomeWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yrk
 * @date 2020/6/17 - 11:00
 */
@RestController
public class DeleteHomeWorkController {

    private static Logger LOGGER = LoggerFactory.getLogger(HomeworkZipDownLoadController.class);
    private OSSClient ossClient = OSSUtil.getOSSClient();
    @Autowired
    IDeleteHomeWorkService deleteHomeWork;

    @GetMapping("/deleteHomeWork")
    public Object deleteFile(@RequestParam("taskId") String taskId, @RequestParam("className") String className) {

        boolean flag = false;
        String targetFloder = OSSClientConstants.HOMEWORKFOLDER + "1/" + "/" + className + "/" + taskId;
        try {
            flag = deleteHomeWork.deleteFile(ossClient, targetFloder);

        } catch (Exception e) {
            LOGGER.error("删除失败", e.getMessage());
        }
        if (flag){
            return ResultUtil.success(flag);
        }else {
            return ResultUtil.fail("删除失败");
        }
    }
}
