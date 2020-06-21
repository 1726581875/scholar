package org.scholat.privider.task.teacherTask.service.impl;

import com.aliyun.oss.OSSClient;
import org.scholat.common.aliyunOSS.OSSUtil;
import org.scholat.privider.task.teacherTask.service.IhomeworkZipDownLoadService;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author yrk
 * @date 2020/6/16 - 10:29
 */
@Service
public class HomeworkZipDownLoadServiceImpl implements IhomeworkZipDownLoadService {
    @Override
    public File homeworkZipDownLoad(OSSClient ossClient, String targetFloder, File TempFile) {
        File  zipFileName = OSSUtil.homeworkZipDownLoad(ossClient, targetFloder, TempFile);
        return zipFileName;
    }
}
