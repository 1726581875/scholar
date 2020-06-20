package org.scholat.privider.task.teacherTask.service.impl;

import com.aliyun.oss.OSSClient;
import org.scholat.common.aliyunOSS.OSSUtil;
import org.scholat.privider.task.teacherTask.service.IDeleteHomeWorkService;
import org.springframework.stereotype.Service;

/**
 * @author yrk
 * @date 2020/6/17 - 10:58
 */
@Service
public class DeleteHomeWorkImpl implements IDeleteHomeWorkService {
    @Override
    public boolean deleteFile(OSSClient ossClient, String targetFloder) {
        return OSSUtil.deleteFile(ossClient, targetFloder);
    }
}
