package org.scholat.privider.task.teacherTask.controller;

import com.aliyun.oss.OSSClient;
import org.scholat.common.aliyunOSS.OSSClientConstants;
import org.scholat.common.aliyunOSS.OSSUtil;
import org.scholat.common.utils.ResultUtil;
import org.scholat.privider.task.teacherTask.service.IMutilUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yrk
 * @date 2020/6/15 - 16:08
 */
@Controller
@RequestMapping("/task")
public class MutilUploadContoller {

    private static Logger LOGGER = LoggerFactory.getLogger(MutilUploadContoller.class);
    private OSSClient ossClient = OSSUtil.getOSSClient();
    private String bucketName = OSSClientConstants.BACKET_NAME;
    private String RESOURSEFOLDER = OSSClientConstants.RESOURSEFOLDER;

    @Autowired
    private IMutilUploadService mutilUploadService;

    @RequestMapping(value = "/uploadResources", method = RequestMethod.POST)
    @ResponseBody
    public Object mutilUploadResourceToOSS(@RequestParam("resources") MultipartFile[] files, @RequestParam("courseId") String courseId) {
        for (MultipartFile file : files) {
            System.err.println("MultipartFile：" + file.isEmpty());
        }
        boolean flag = true;
        if (files != null && files.length > 0 && !files[0].isEmpty()) {
            long beginTime = System.currentTimeMillis();
            LOGGER.info("====> 上传教学资源开始 <====");
            for (int i = 0; i < files.length; i++) {
                String upLoadFolder = RESOURSEFOLDER + "教师名字/" + courseId + "/" + files[i].getOriginalFilename();
                try {
                    mutilUploadService.mutilUploadResourceToOSS(ossClient, bucketName, files[i], upLoadFolder);
                } catch (Exception e) {
                    flag = false;
                    LOGGER.error("====> 上传教学资源失败！<====" + e.getMessage());
                }
            }
            LOGGER.info("====> 上传教学资源结束 <====");
            long endTime = System.currentTimeMillis();
            Long sumTime = endTime - beginTime;
            LOGGER.info(" 上传教学资源总耗时：===> " + sumTime.toString());
        } else {
            System.err.println("====> 上传教学资源为空！<====");
            LOGGER.error("====> 上传教学资源为空！<====");
            flag = false;
        }
        //返回上传的信息
        if (flag) {
            return ResultUtil.success("上传教学资源成功!");
        } else {
            return ResultUtil.fail("上传教学资源失败！上传教学资源不能为空！");
        }
    }

}
