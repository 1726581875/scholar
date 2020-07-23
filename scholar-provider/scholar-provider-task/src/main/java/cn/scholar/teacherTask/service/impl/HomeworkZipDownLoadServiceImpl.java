package cn.scholar.teacherTask.service.impl;

import cn.scholar.common.aliyunOSS.OSSUtil;
import cn.scholar.teacherTask.service.IhomeworkZipDownLoadService;
import com.aliyun.oss.OSSClient;
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
