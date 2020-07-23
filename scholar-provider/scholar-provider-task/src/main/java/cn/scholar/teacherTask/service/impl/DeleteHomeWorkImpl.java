package cn.scholar.teacherTask.service.impl;

import cn.scholar.common.aliyunOSS.OSSUtil;
import cn.scholar.teacherTask.service.IDeleteHomeWorkService;
import com.aliyun.oss.OSSClient;
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
