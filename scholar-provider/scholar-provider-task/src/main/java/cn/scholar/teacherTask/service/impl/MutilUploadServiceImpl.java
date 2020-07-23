package cn.scholar.teacherTask.service.impl;

import cn.scholar.common.aliyunOSS.OSSUtil;
import com.aliyun.oss.OSSClient;
import cn.scholar.teacherTask.service.IMutilUploadService;
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
