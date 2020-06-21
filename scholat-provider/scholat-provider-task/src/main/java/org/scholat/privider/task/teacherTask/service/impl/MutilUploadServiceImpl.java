package org.scholat.privider.task.teacherTask.service.impl;

import com.aliyun.oss.OSSClient;
import org.scholat.common.aliyunOSS.OSSUtil;
import org.scholat.privider.task.teacherTask.service.IMutilUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yrk
 * @date 2020/6/15 - 15:39
 */
@Service
public class MutilUploadServiceImpl implements IMutilUploadService {

    /**
     * 上传教学资源至OSS（多文件上传）
     * @param ossClient
     * @param bucketName
     * @param file
     * @param uploadFolder
     * @return
     */
    @Override
    public String mutilUploadResourceToOSS(OSSClient ossClient, String bucketName, MultipartFile file, String uploadFolder) {

        String  resultStr = OSSUtil.uploadFileToOSS(ossClient, bucketName, file, uploadFolder);
        return resultStr;
    }
}
