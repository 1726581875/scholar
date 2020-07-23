package cn.scholar.teacherTask.service;

import com.aliyun.oss.OSSClient;

/**
 * @author yrk
 * @date 2020/6/17 - 10:57
 */
public interface IDeleteHomeWorkService {
    /**
     * 删除OSS上的文件
     * @param ossClient
     * @param targetFloder
     * @return
     */
    public boolean deleteFile(OSSClient ossClient, String targetFloder);
}
