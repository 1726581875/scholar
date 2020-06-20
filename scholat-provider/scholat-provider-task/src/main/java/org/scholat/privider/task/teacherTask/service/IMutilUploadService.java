package org.scholat.privider.task.teacherTask.service;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yrk
 * @date 2020/6/15 - 15:38
 */
public interface IMutilUploadService {
    /**
     * 上传教学资源至OSS（多文件上传）
     * @param ossClient
     * @param bucketName
     * @param file
     * @param uploadFolder
     * @return
     */
    public String mutilUploadResourceToOSS(OSSClient ossClient, String bucketName, MultipartFile file, String uploadFolder);
}
