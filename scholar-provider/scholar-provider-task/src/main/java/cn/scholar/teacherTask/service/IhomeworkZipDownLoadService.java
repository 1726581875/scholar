package cn.scholar.teacherTask.service;

import com.aliyun.oss.OSSClient;

import java.io.File;

/** 批量打包压缩下载
 * @author yrk
 * @date 2020/6/16 - 10:18
 */
public interface IhomeworkZipDownLoadService {
    /**
     *
     * @param ossClient
     * @param targetFloder
     * @return
     */
    File homeworkZipDownLoad(OSSClient ossClient, String targetFloder, File TempFile);
}
