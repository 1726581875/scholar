package cn.scholar.studentHomeWork.controller;

import cn.scholar.common.aliyunOSS.OSSUtil;
import com.aliyun.oss.OSSClient;
import cn.scholar.common.aliyunOSS.OSSClientConstants;
import cn.scholar.common.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author yrk
 * @date 2020/6/14 - 23:28
 */
@Controller
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
public class UploadHomeWorkController {

    private static Logger LOGGER = LoggerFactory.getLogger(UploadHomeWorkController.class);
    private OSSClient ossClient = OSSUtil.getOSSClient();
    private  String bucketName = OSSClientConstants.BACKET_NAME;
    /**
     * @param file :上传的文件
     * @param courseId ：课程id，作为文件上传的一级目录，班级作为二级目录
     * @param taskId ：教师布置的作业id，作为文件上传的三级目录
     * @return
     */
    @RequestMapping(value = "/uploadHomeWork", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFileToOSS(@RequestParam("homeWork") MultipartFile file, @RequestParam("courseId") String courseId , @RequestParam("taskId") String taskId) throws IOException {
        System.err.println("=====> uploadFileToOSS以执行 <=====");
        if (file != null && !file.isEmpty()) {
            //文件上传存储在oss上的位置
            String fileDir = OSSClientConstants.HOMEWORKFOLDER + courseId + "/" + "17外包2班/" + taskId + "/" + file.getOriginalFilename();
            String uploadResult = OSSUtil.uploadFileToOSS(ossClient, bucketName, file, fileDir);
            if (uploadResult != null && uploadResult != "") {
                LOGGER.info("作业上传成功！");
                return ResultUtil.success(uploadResult);
            } else {
                LOGGER.error("作业上传失败！");
                return ResultUtil.fail("上传失败");
            }
        } else {
            LOGGER.error("作业上传失败！");
            return ResultUtil.fail("上传失败, 上传文件不能为空！");
        }
    }
}
